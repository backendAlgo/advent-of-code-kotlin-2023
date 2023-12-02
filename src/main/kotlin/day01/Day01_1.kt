package main.kotlin.day01

import main.kotlin.*

fun main() {
  val profile = "real"
  val dayId = "01"

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val first = line.first { it.isDigit() }
      val last = line.last { it.isDigit() }
      first.digitToInt() * 10 + last.digitToInt()
    }
  }

  fun part2(input: List<String>): Int {
    val wordDigit = mapOf(
      "one" to 1,
      "two" to 2,
      "three" to 3,
      "four" to 4,
      "five" to 5,
      "six" to 6,
      "seven" to 7,
      "eight" to 8,
      "nine" to 9,
    )
    return input.sumOf { line ->
      val first = minOf(line.indexOfFirstOrMax { it.isDigit() }, line.indexOfAnyOrMax(wordDigit.keys))
      val last = maxOf(line.indexOfLast { it.isDigit() }, line.lastIndexOfAny(wordDigit.keys))
      line.getDigit(first, wordDigit) * 10 + line.getDigit(last, wordDigit)
    }
  }


  val input = readInput(dayId, profile)
//  part1(input).println()
//  check(part1(input) == 24000)
  part2(input).println()
}


