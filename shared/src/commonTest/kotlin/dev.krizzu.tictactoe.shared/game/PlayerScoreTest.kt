package dev.krizzu.tictactoe.shared.game

import kotlin.test.Test
import kotlin.test.assertTrue

class PlayerScoreTest {

  @Test
  fun `Finds winner for first row`() {
    val score = PlayerScore()

    score.addScore(0)
    score.addScore(1)
    score.addScore(2)

    assertTrue(score.isWinner)
  }

  @Test
  fun `Finds winner for diagonal win`() {
    val score = PlayerScore()
    score.addScore(0)
    score.addScore(4)
    score.addScore(8)

    assertTrue(score.isWinner)
  }

  @Test
  fun `Finds winner for column win`() {
    val score = PlayerScore()
    score.addScore(0)
    score.addScore(3)
    score.addScore(6)
  }
}