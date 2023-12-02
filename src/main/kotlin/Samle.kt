package main.kotlin

fun main() {
  val profile = "basic"
  val dayId = "00"

  fun part1(input: List<String>): Int {
    return input.size
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  val input = readInput(dayId, profile)

  part1(input).println()
  check(part1(input) == 1)

//  part2(input).println()
}
