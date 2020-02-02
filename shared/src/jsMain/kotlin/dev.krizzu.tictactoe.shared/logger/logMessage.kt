package dev.krizzu.tictactoe.shared.logger

actual fun logMessage(level: LOG_LEVEL, message: Any?) {
  when (level) {
    LOG_LEVEL.INFO -> {
      console.info("[TicTacToe]", message)
    }
    LOG_LEVEL.ERROR -> {
      console.error("[TicTacToe]", message)
    }
    LOG_LEVEL.DEBUG -> {
      console.log("[TicTacToe]", message)
    }
    LOG_LEVEL.WARN -> {
      console.warn("[TicTacToe]", message)
    }
  }
}