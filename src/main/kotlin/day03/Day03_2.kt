package main.kotlin.day03

import main.kotlin.println
import main.kotlin.productOf
import main.kotlin.readInput


enum class Directions(val rowChange: Int, val colChange: Int) {
  LEFT(0, -1), RIGHT(0, 1), TOP(-1, 0), DOWN(1, 0), LEFTOP(-1, -1), LEFTDOWN(1, -1), RIGHTTOP(-1, 1), RIGHTDOWN(1, 1);


  fun getAdjacent(row: Int, col: Int): Pair<Int, Int> = (row + rowChange) to (col + colChange)
}

fun main() {
  val profile = "real"
  val dayId = "03"

  fun getAdjacentNumbers(row: Int, col: Int, input: List<String>): List<Int> {
    val ints = mutableListOf<Int>()
    val numberSet = mutableSetOf<Pair<Int, Int>>()
    for (entry in Directions.entries) {
      val (adjacentRow, adjacentCol) = entry.getAdjacent(row, col)
      input.getOrNull(adjacentRow)?.let { line ->
        line.getOrNull(adjacentCol)?.let { char ->
          if (char.isDigit()) {
            var start = adjacentCol
            var end = adjacentCol
            while (start > 0 && line[start - 1].isDigit()) {
              start--
            }
            while (end < line.lastIndex && line[end + 1].isDigit()) {
              end++
            }
            with(adjacentRow to start) {
              if (this !in numberSet) {
                ints += line.substring(start, end + 1).toInt()
                numberSet += this
              }
            }
          }
        }
      }
    }
    return ints
  }

  fun solve(input: List<String>): Int {
    var sum = 0
    for (row in 0..input.lastIndex) {
      for (col in 0..input[row].lastIndex) {
        if (input[row][col] == '*') {
          val adjacentNumbers = getAdjacentNumbers(row, col, input)
          if (adjacentNumbers.size == 2) {
            sum += adjacentNumbers.productOf { it }
          }
        }
      }
    }
    return sum
  }

  val input = readInput(dayId, profile)

  solve(input).println()
//  check(solve(input) == 467835)
}
