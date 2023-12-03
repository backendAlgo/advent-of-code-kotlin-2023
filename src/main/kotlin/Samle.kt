package main.kotlin

import main.kotlin.println
import main.kotlin.readInput

fun main() {
  val profile = "basic"
  val dayId = "00"

  fun solve(input: List<String>): Int {
    return input.size
  }

  val input = readInput(dayId, profile)

  solve(input).println()
  check(solve(input) == 1)
}
