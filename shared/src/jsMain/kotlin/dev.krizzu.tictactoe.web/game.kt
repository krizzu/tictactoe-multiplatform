package dev.krizzu.tictactoe.web

import org.w3c.dom.Element
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLCollection
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.get
import dev.krizzu.tictactoe.shared.game.GameType
import dev.krizzu.tictactoe.shared.game.PlayerSign
import dev.krizzu.tictactoe.shared.game.TicTacToeGame
import dev.krizzu.tictactoe.shared.logger.Logger
import kotlin.browser.document

class Game {

  private val ticTacToeGame = TicTacToeGame()
  private lateinit var fieldButtons: HTMLCollection
  private var alreadyInitialized = false

  private val vsCpuInput: HTMLInputElement by lazy {
    document.getElementById("vs-cpu") as HTMLInputElement
  }
  private val playerXInput: HTMLInputElement by lazy {
    document.getElementById("player-sign-x") as HTMLInputElement
  }

  private val startGameButton: HTMLButtonElement by lazy {
    document.getElementById("game-start-button") as HTMLButtonElement
  }

  private val announcer: HTMLElement by lazy {
    document.getElementById("announcer") as HTMLElement
  }

  init {
    initButtons()
    startNewGame()
  }

  private fun initButtons() {

    if (alreadyInitialized) return

    startGameButton.addEventListener("click", ::startNewGame)

    fieldButtons = document.getElementsByClassName("playField")

    for (index in 0 until fieldButtons.length) {

      val button = fieldButtons[index]!!

      val id = button.getAttribute("id")!!


      button.addEventListener("click", {
        handleFieldClick(id.toInt(), button)
      })
    }

    alreadyInitialized = true
  }

  private fun startNewGame(e: Event? = null) {

    // clear buttons
    for (buttonIndex: Int in 0 until fieldButtons.length) {
      val button: Element? = fieldButtons[buttonIndex]
      button?.innerHTML = ""
    }

    announcer.innerText = ""

    ticTacToeGame.selectedGameType =
      if (vsCpuInput.checked) GameType.vsComputer else GameType.vsHuman
    ticTacToeGame.selectedPlayerSign =
      if (playerXInput.checked) PlayerSign.X else PlayerSign.O


    ticTacToeGame.startNewGame()

    if (ticTacToeGame.currentGameType == GameType.vsComputer) {
      handleCpuMove()
    }
  }

  private fun checkGameStatus() {
    if (ticTacToeGame.isGameOver) {
      if (ticTacToeGame.winner != null) {
        announcer.innerText = "${ticTacToeGame.winner} wins!"
      } else {
        announcer.innerText = "Game over!"
      }
    }
  }

  private fun handleCpuMove() {
    if (ticTacToeGame.currentGameType !== GameType.vsComputer || ticTacToeGame.currentPlayer == ticTacToeGame.humanPlayerSign || ticTacToeGame.isGameOver) return

    val cpuMove = ticTacToeGame.play()
    if (cpuMove != null) {
      val move = cpuMove.toInt()
      val button = fieldButtons[move]
      button?.innerHTML = ticTacToeGame.getSignAt(cpuMove).toString()
    }

    checkGameStatus()
  }

  private fun handleFieldClick(field: Int, button: Element) {
    val fieldConfirm = ticTacToeGame.play(field.toByte())

    if (fieldConfirm != null) {
      Logger.d("Correct move to field: $fieldConfirm")
      button.innerHTML = ticTacToeGame.getSignAt(fieldConfirm).toString()
    } else {
      Logger.d("Invalid move: $field")
    }

    if (ticTacToeGame.isGameOver) {
      checkGameStatus()
    } else {
      handleCpuMove()
    }
  }
}