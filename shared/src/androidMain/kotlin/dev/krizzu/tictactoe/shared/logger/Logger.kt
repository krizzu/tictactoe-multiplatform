package dev.krizzu.tictactoe.shared.logger

import android.util.Log
import dev.krizzu.tictactoe.shared.logger.LOG_LEVEL

actual fun logMessage(level: LOG_LEVEL, message: Any?) {
  when (level) {
    LOG_LEVEL.DEBUG -> Log.d("[TicTacToe]", message as String)
    LOG_LEVEL.INFO -> Log.i("[TicTacToe]", message as String)
    LOG_LEVEL.WARN -> Log.w("[TicTacToe]", message as String)
    LOG_LEVEL.ERROR -> Log.e("[TicTacToe]", message as String)
  }
}