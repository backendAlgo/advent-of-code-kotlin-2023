package main.kotlin.day02

import main.kotlin.println
import main.kotlin.productOf
import main.kotlin.readInput

fun main() {
  val profile = "real"
  val dayId = "02"

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val (gameIdStr, cubes) = line.split(": ")
      if (cubes.split("; ")
          .all { cube ->
            cube.split(", ")
              .all { it.isColorPossible() }
          }
      ) {
        gameIdStr.getGameId()
      } else {
        0
      }
    }
  }

  fun part2(input: List<String>): Int {
    return input.sumOf { line ->
      val (_, cubes) = line.split(": ")

      val maxValues = cubes.split("; ")
        .flatMap { cube ->
          cube.split(", ")
            .map { it.colorToValue() }

        }
        .groupingBy { it.first }
        .fold(0) { accumulator, element -> maxOf(accumulator, element.second) }
        .values
      if (maxValues.size < 3) {
        0
      } else {
        maxValues.productOf { it }
      }
    }
  }

  val input = readInput(dayId, profile)

  part1(input).println()
//  check(part1(input) == 8)

  part2(input).println()
}

private fun String.colorToValue(): Pair<String, Int> {
  val (num, color) = this.split(" ")
  return color to num.toInt()
}

private fun String.isColorPossible(): Boolean {
  val constraintMap = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
  )
  val (num, color) = this.split(" ")
  return num.toInt() <= constraintMap[color]!!
}


private fun String.getGameId(): Int = this
  .takeLastWhile { it.isDigit() }
  .toInt()
