package dev.krizzu.tictactoe.androidApp.views

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.krizzu.tictactoe.androidApp.R
import dev.krizzu.tictactoe.shared.game.GameType
import dev.krizzu.tictactoe.shared.game.PlayerSign
import dev.krizzu.tictactoe.shared.game.TicTacToeGame

class MainActivity : AppCompatActivity() {

  val gameController: TicTacToeGame =
    TicTacToeGame()
  private lateinit var startGameButton: Button
  private lateinit var gameResult: TextView
  private var gameBoardFragment: GameBoardFragment? = null
  private var signSelectFragment: SignSelectFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)

    startGameButton = findViewById(R.id.button_startGame)
    gameResult = findViewById(R.id.text_gameResult)

    gameBoardFragment =
      supportFragmentManager.findFragmentById(R.id.gameBoard_fragment) as GameBoardFragment

    signSelectFragment =
      supportFragmentManager.findFragmentById(R.id.playerSign_fragment) as SignSelectFragment

    startGameButton.setOnClickListener {
      clearAndStartGame()
    }

    onGameTypeChange(gameController.selectedGameType)
  }

  internal fun onFieldPress(field: Byte?): PlayerSign? {
    val isSuccess = gameController.play(field) != null
    var playerSign: PlayerSign? = null
    if (isSuccess) {
      playerSign = gameController.getSignAt(field!!)
    }

    if (gameController.isGameOver) {
      checkGameResult()
    } else {
      handleCpuMove()
    }

    return playerSign
  }

  internal fun onSignChange(newSign: PlayerSign) {
    gameController.selectedPlayerSign = newSign
  }

  internal fun onGameTypeChange(newGameType: GameType) {
    gameController.selectedGameType = newGameType
    signSelectFragment?.onGameTypeChange(newGameType == GameType.vsComputer)
  }

  private fun clearAndStartGame() {
    gameResult.text = ""
    gameResult.visibility = View.INVISIBLE
    gameBoardFragment?.clearFields()
    gameController.startNewGame()

    if (gameController.currentGameType == GameType.vsComputer) {
      handleCpuMove()
    }
  }

  private fun checkGameResult() {
    if (gameController.isGameOver) {
      if (gameController.winner != null) {
        gameResult.text = "${gameController.winner.toString()} wins!"
      } else {
        gameResult.text = "Game Over!"
      }
      gameResult.visibility = View.VISIBLE
    }
  }

  private fun handleCpuMove() {
    if (gameController.currentGameType !== GameType.vsComputer
      || gameController.currentPlayer == gameController.humanPlayerSign
      || gameController.isGameOver
    ) {
      return
    }

    val cpuMove = gameController.play()

    if (cpuMove != null) {
      gameBoardFragment?.placeSignAt(gameController.getSignAt(cpuMove)!!, cpuMove)
    }

    checkGameResult()
  }
}
