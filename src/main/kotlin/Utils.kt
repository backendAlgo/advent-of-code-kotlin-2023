package main.kotlin

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Comparator

/**
 * Reads lines from the given input txt file.
 */
fun readInput(dayId: String, profile: String) = File(
  "src/main/resources/day$dayId",
  "Day${dayId}_$profile.txt"
)
  .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
  .toString(16)
  .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Create list from input, separating with blank string
 */
fun <T> List<String>.parts(map: (List<String>) -> T): List<T> = buildList {
  var currList = mutableListOf<String>()
  for (str in this@parts) {
    if (str.isBlank()) {
      add(map(currList))
      currList = mutableListOf()
    } else {
      currList.add(str)
    }
  }
  if (currList.isNotEmpty()) {
    add(map(currList))
  }
}

fun <T : Comparable<T>> compareNullsFirst(): Comparator<T> =
  Comparator { a, b -> if (a == null) 1 else if (b == null) -1 else a.compareTo(b) }

fun <T : Comparable<T>> Comparator<T>.compareNullsLast(): Comparator<T> =
  Comparator { a, b -> if (a == null) -1 else if (b == null) 1 else a.compareTo(b) }

fun CharSequence.indexOfFirstOrMax(predicate: (Char) -> Boolean): Int {
  return this.indexOfFirst(predicate).let { if (it == -1) this.length else it }
}

fun CharSequence.indexOfAnyOrMax(strings: Collection<String>, startIndex: Int = 0, ignoreCase: Boolean = false): Int {
  return this.indexOfAny(strings, startIndex, ignoreCase).let { if (it == -1) this.length else it }
}

fun String.getDigit(index: Int, wordDigit: Map<String, Int>): Int {
  if (this[index].isDigit()) {
    return this[index].digitToInt()
  }
  for ((key, value) in wordDigit) {
    if (index + key.length <= length && this.substring(index, index + key.length) == key) {
      return value
    }
  }
  throw IllegalStateException("cannot be here")
}

inline fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int {
  var product = 1
  for (element in this) {
    product *= selector(element).also {
      if (it == 0) {
        return 0
      }
    }
  }
  return product
}


