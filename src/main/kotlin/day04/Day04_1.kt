package main.kotlin.day04

import main.kotlin.println
import main.kotlin.readInput

fun main() {
  val profile = "real"
  val dayId = "04"

  fun solve(input: List<String>): Int {
    val sizeToScoreList = input.map {
      val numbers = """Card\s+(\d+): (.+)""".toRegex().find(it)?.groupValues?.get(2)
      checkNotNull(numbers) { "format mismatch" }

      val (winningNumbers, actualNumbers) = numbers.split("|").map {
        it.trim().split("\\s+".toRegex()).map { num -> num.toInt() }
      }
      val winningNumbersSet = winningNumbers.toSet()
      val winCards = actualNumbers.filter { it in winningNumbersSet }
      val size = winCards.size
      size
    }
    val copiesCount = IntArray(sizeToScoreList.size) { 0 }
    var copySum = 0
    for ((index, size) in sizeToScoreList.withIndex()) {
      val copy = copiesCount[index]
      copySum += copy + 1
      for (copyIndex in index + 1..index + size) {
        copiesCount[copyIndex] += copy + 1
      }
    }
    return copySum
  }

  val input = readInput(dayId, profile)

  solve(input).println()
//  check(solve(input) == 13)
}
