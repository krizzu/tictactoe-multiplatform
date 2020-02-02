package dev.krizzu.tictactoe.shared.logger

enum class LOG_LEVEL {
  DEBUG,
  INFO,
  WARN,
  ERROR
}

expect fun logMessage(level: LOG_LEVEL, message: Any?)

object Logger {
  fun i(message: Any?) {
    logMessage(
      LOG_LEVEL.INFO,
      message
    )
  }

  fun d(message: Any?) {
    logMessage(
      LOG_LEVEL.DEBUG,
      message
    )
  }

  fun w(message: Any?) {
    logMessage(
      LOG_LEVEL.WARN,
      message
    )
  }

  fun e(message: Any?) {
    logMessage(
      LOG_LEVEL.ERROR,
      message
    )
  }
}