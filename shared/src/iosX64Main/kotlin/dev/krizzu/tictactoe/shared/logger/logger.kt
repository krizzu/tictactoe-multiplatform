package dev.krizzu.tictactoe.shared.logger

import platform.Foundation.NSLog

actual fun logMessage(level: LOG_LEVEL, message: Any?) {
  NSLog("%@", message)
}