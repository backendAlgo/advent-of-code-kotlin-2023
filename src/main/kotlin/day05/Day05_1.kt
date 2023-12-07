package main.kotlin.day05

import main.kotlin.println
import main.kotlin.readInput

fun main() {
  val profile = "basic"
  val dayId = "05"

  val nameToIndex = mapOf(
    "seeds" to 0,
    "seed-to-soil" to 1,
    "soil-to-fertilizer" to 2,
    "fertilizer-to-water" to 3,
    "water-to-light" to 4,
    "light-to-temperature" to 5,
    "temperature-to-humidity" to 6,
    "humidity-to-location" to 7
  )

  fun generateList(part: List<String>): List<List<Long>> {
    return part.drop(1).map { line ->
      line.split(" ").map { it.toLong() }
    }.sortedBy { it[1] }
  }

  fun List<String>.splitByBlankLine(): List<List<String>> = buildList {
    var currPart = ArrayList<String>()
    for (line in this@splitByBlankLine) {
      if (line.isBlank()) {
        add(currPart)
        currPart = ArrayList()
        continue
      }
      currPart += line
    }

    if (currPart.isNotEmpty()) {
      add(currPart)
    }
  }


  fun findForEachSeed(seed: Long, destination: List<List<Long>>): Long {
    var low = 0
    var high = destination.size
    while (low < high) {
      val mid = (low + high) / 2
      if (destination[mid][1] > seed) {
        high = mid
      } else {
        low = mid + 1
      }
    }
    return destination.getOrNull(low - 1)?.let { (dest, source, range) ->
      if (seed in source..<source + range) {
        dest + (seed - source)
      } else {
        null
      }
    } ?: seed
  }


  fun findSeedDestination(seeds: List<Long>, destination: List<List<Long>>): List<Long> {
    return seeds.map { findForEachSeed(it, destination) }
  }

  fun updateSeedDestination(seeds: MutableList<Long>, list: List<Long>) {
    val (dest, source, range) = list
    for ((index, seed) in seeds.withIndex()) {
      if (seed in source..<source + range) {
        seeds[index] = dest + (seed - source)
      }
    }
  }

  fun solve(input: List<String>): Long {
    var seeds = input[0].substringAfter(" ").split(" ").map { it.toLong() }
      .chunked(2).flatMap { (start, range) ->
      (start..<start + range).map { it }
    }.toMutableList()
    var start = 3
    while (start < input.size) {
      var curr = start
      while (curr < input.size && input[curr].isNotBlank()) {
        val list = input[curr].split(" ").map {
          it.toLong()
        }
        updateSeedDestination(seeds, list)
        println(seeds[3])
        curr++
      }
      start = curr + 2
    }


    return seeds.min()
  }


  val input = readInput(dayId, profile)

  solve(input).println()
//  check(solve(input) == 46L)
}
