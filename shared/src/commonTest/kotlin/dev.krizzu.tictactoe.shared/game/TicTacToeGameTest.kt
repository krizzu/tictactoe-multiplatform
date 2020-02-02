package dev.krizzu.tictactoe.shared.game

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TicTacToeGameTest {

  @Test
  fun `Places a sign in board`() {
    val game = TicTacToeGame()

    game.play(3) // O
    game.play(7) // X
    game.play(0) // O
    game.play(5) // X

    assertEquals(PlayerSign.O, game.getSignAt(3))
    assertEquals(PlayerSign.X, game.getSignAt(7))
    assertEquals(PlayerSign.O, game.getSignAt(0))
    assertEquals(PlayerSign.X, game.getSignAt(5))
  }

  @Test
  fun `Does not allow sign to be placed if field is occupied`() {
    val game = TicTacToeGame()

    val placedO = game.play(3)
    val placedY = game.play(3)

    assertEquals(3, placedO)
    assertNull(placedY)

    assertEquals(PlayerSign.O, game.getSignAt(3))
  }

  @Test
  fun `Resets the board on new game start`() {
    val game = TicTacToeGame()

    game.play(0)

    assertEquals(PlayerSign.O, game.getSignAt(0))

    game.startNewGame(GameType.vsHuman)


    assertEquals(null, game.getSignAt(0))
  }

  @Test
  fun `Cpu makes random moves`() {
    val game = TicTacToeGame()

    game.startNewGame(GameType.vsComputer, PlayerSign.O)

    assertEquals(PlayerSign.O, game.currentPlayer)

    game.play(3)

    assertEquals(PlayerSign.O, game.getSignAt(3))

    val cpuMove = game.play()

    assertNotNull(cpuMove)

    assertEquals(PlayerSign.X, game.getSignAt(cpuMove))

    assertEquals(PlayerSign.O, game.currentPlayer)
  }

  @Test
  fun `Finds proper winner for game`() {
    val game = TicTacToeGame()

    game.play(0) // O
    game.play(1) // X
    game.play(3) // O
    game.play(2) // X
    game.play(6) // O

    assertEquals(PlayerSign.O, game.winner, "Winner not found!")
  }

  @Test
  fun `Do not allow moves if there's a winner`() {
    val game = TicTacToeGame()

    game.play(0) // O
    game.play(1) // X
    game.play(3) // O
    game.play(2) // X
    game.play(6) // O

    assertEquals(null, game.play(7), "Moved should not be allowed")
  }

  @Test
  fun `Marks game as over if there are no move moves`() {
    val game = TicTacToeGame()
    game.play(0) // O
    game.play(1) // X
    game.play(2) // O
    game.play(5) // X
    game.play(3) // O
    game.play(6) // X
    game.play(4) // O
    game.play(8) // X
    game.play(7) // O

    assertTrue(game.isGameOver, "Game is not marked as over!")
  }

  @Test
  fun `Marks game as over if there is a winner`() {
    val game = TicTacToeGame()
    game.play(0) // O
    game.play(3) // X
    game.play(1) // O
    game.play(4) // X
    game.play(2) // O

    assertTrue(game.isGameOver, "Game is not marked as over!")
  }
}