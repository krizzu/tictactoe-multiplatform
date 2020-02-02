package dev.krizzu.tictactoe.shared.game

class PlayerScore {
  private var firstRowScore = 0
  private var secondRowScore = 0
  private var thirdRowScore = 0

  private var firstColumnScore = 0
  private var secondColumnScore = 0
  private var thirdColumnScore = 0

  private var leftToRightDiagonalScore = 0
  private var rightToLeftDiagonalScore = 0

  /*
      To make finding the winner efficient, O(1), keep track of each player move and score it
      This works for any grid size - 3x3 grid means you need to score 3 points to wins

      Each field in grid has unique number
           0 | 1 | 2
           3 | 4 | 5
           6 | 7 | 8
      We keep track of score by applying score to the field

   */
  fun addScore(field: Int) {
    when (field) {
      // top left move
      0 -> {
        firstRowScore++
        firstColumnScore++
        leftToRightDiagonalScore++
      }
      1 -> {
        firstRowScore++
        secondColumnScore++
      }
      2 -> {
        firstRowScore++
        thirdColumnScore++
        rightToLeftDiagonalScore++
      }
      3 -> {
        secondRowScore++
        firstColumnScore++
      }
      4 -> {
        secondRowScore++
        secondColumnScore++
        leftToRightDiagonalScore++
        rightToLeftDiagonalScore++
      }
      5 -> {
        secondRowScore++
        thirdColumnScore++
      }
      6 -> {
        thirdRowScore++
        firstColumnScore++
        rightToLeftDiagonalScore++
      }
      7 -> {
        thirdRowScore++
        secondColumnScore++
      }
      8 -> {
        thirdRowScore++
        leftToRightDiagonalScore++
        thirdColumnScore++
      }
    }
  }

  val isWinner: Boolean
    get() {
      return when {
        firstRowScore > 2 -> true
        secondRowScore > 2 -> true
        thirdRowScore > 2 -> true
        firstColumnScore > 2 -> true
        secondColumnScore > 2 -> true
        thirdColumnScore > 2 -> true
        leftToRightDiagonalScore > 2 -> true
        rightToLeftDiagonalScore > 2 -> true
        else -> false
      }
    }
}