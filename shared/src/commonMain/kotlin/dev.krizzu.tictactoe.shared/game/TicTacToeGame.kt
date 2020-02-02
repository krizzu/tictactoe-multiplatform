package dev.krizzu.tictactoe.shared.game

import kotlin.random.Random

typealias GameBoard = Array<Array<PlayerSign?>>

/*
  Rules:
  1. O always go first
 */


class TicTacToeGame {

  var selectedGameType: GameType =
    GameType.vsHuman
  var selectedPlayerSign: PlayerSign =
    PlayerSign.O

  private lateinit var gameBoard: GameBoard
  private var movesDone = 0
  var currentGameType = GameType.vsHuman
    private set
  var currentPlayer = PlayerSign.O
    private set
  var humanPlayerSign: PlayerSign =
    PlayerSign.O
    private set
  private var score = mapOf(
    PlayerSign.O to PlayerScore(),
    PlayerSign.X to PlayerScore()
  )

  val nextPlayer: PlayerSign
    get() = if (currentPlayer == PlayerSign.X) PlayerSign.O else PlayerSign.X

  init {
    startNewGame(GameType.vsHuman)
  }

  fun startNewGame() {
    startNewGame(selectedGameType, selectedPlayerSign)
  }

  fun startNewGame(gameType: GameType, playerSign: PlayerSign = PlayerSign.O) {
    resetGame()
    currentPlayer = PlayerSign.O
    humanPlayerSign = playerSign
    movesDone = 0
    currentGameType = gameType
  }

  fun play(field: Byte? = null): Byte? {
    /*
     Each game field is labeled with a number
           0 | 1 | 2
           3 | 4 | 5
           6 | 7 | 8
     Play the game by giving field number to place a sign.
     Returns null if move cannot be completed.
     Otherwise returns field that the sign has been placed at.
    */

    return if (currentGameType == GameType.vsHuman || currentPlayer == humanPlayerSign) {
      makePlayerMove(field)
    } else {
      makeCpuMove()
    }
  }

  fun getSignAt(field: Byte): PlayerSign? {
    val x = field / 3
    val y = field % 3
    return gameBoard[x][y]
  }

  val winner: PlayerSign?
    get() {
      return when {
        score[PlayerSign.O]?.isWinner == true -> PlayerSign.O
        score[PlayerSign.X]?.isWinner == true -> PlayerSign.X
        else -> null
      }
    }

  val isGameOver: Boolean
    get() = winner != null || movesDone > 8

  private fun makeCpuMove(): Byte? {

    if (isGameOver) return null

    var move = Random.nextInt(0, 9).toByte()

    while (!isValidMove(move)) {
      move = Random.nextInt(0, 9).toByte()
    }

    makePlayerMove(move)

    return move
  }

  private fun isValidMove(field: Byte): Boolean {
    val x = field / 3
    val y = field % 3

    // fail if field is outside of boundaries
    if (x > 2 || y > 2) {
      return false
    }

    // fail if field is already occupied
    if (gameBoard[x][y] != null) {
      return false
    }

    return true
  }

  private fun makePlayerMove(field: Byte?): Byte? {

    if (field == null || !isValidMove(field) || winner != null) {
      return null
    }

    val x = field / 3
    val y = field % 3

    val sign = currentPlayer

    gameBoard[x][y] = sign

    currentPlayer = if (currentPlayer == PlayerSign.O) PlayerSign.X else PlayerSign.O

    movesDone++

    score[sign]?.addScore(field.toInt())

    return field
  }

  private fun resetGame() {
    gameBoard = arrayOf(
      arrayOf<PlayerSign?>(null, null, null),
      arrayOf<PlayerSign?>(null, null, null),
      arrayOf<PlayerSign?>(null, null, null)
    )

    score = mapOf(
      PlayerSign.O to PlayerScore(),
      PlayerSign.X to PlayerScore()
    )
  }
}