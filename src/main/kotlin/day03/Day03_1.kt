package main.kotlin.day03

import main.kotlin.println
import main.kotlin.readInput

fun main() {
  val profile = "real"
  val dayId = "03"

  fun isPartNumber(number: Pair<IntRange, IntRange>, input: List<String>): Boolean {
    for (row in number.first) {
      for (col in number.second) {
        input.getOrNull(row)?.getOrNull(col)?.let {
          if (!it.isDigit() && it != '.') {
            return true
          }
        }
      }
    }
    return false
  }

  fun solve(input: List<String>): Int {
    return input.mapIndexed { rowIndex, line ->
      val lineSum = buildList {
        var start = 0
        while (start <= line.lastIndex) {
          if (line[start].isDigit()) {
            var end = start + 1
            while (end <= line.lastIndex && line[end].isDigit()) {
              end++
            }
            add(rowIndex - 1..rowIndex + 1 to start - 1..end)
            start = end
          }
          start++
        }
      }.filter { isPartNumber(it, input) }.sumOf {
          val (startBefore, endAfter) = it.second
          line.substring(startBefore + 1, endAfter).toInt()
        }
      lineSum
    }.sum()
  }


  val input = readInput(dayId, profile)

  solve(input).println()
//  check(solve(input) == 4361)
}

private operator fun IntRange.component1(): Int = this.start

private operator fun IntRange.component2(): Int = this.endInclusive


