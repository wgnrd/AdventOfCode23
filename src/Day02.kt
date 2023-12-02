import java.util.*

enum class Color {
  RED,
  GREEN,
  BLUE
}

const val MAX_RED = 12
const val MAX_GREEN = 13
const val MAX_BLUE = 14

fun main() {
  val maxValues = listOf(Color.RED to MAX_RED, Color.GREEN to MAX_GREEN, Color.BLUE to MAX_BLUE)

  // This function processes a reveal string (e.g., "3 blue, 2 red") and returns a list of pairs,
  // where each pair consists of a Color and an Int representing the number of that color in the
  // reveal.
  fun processReveal(reveal: String): List<Pair<Color, Int>> {
    val revealsList = reveal.trim().split(", ")
    return revealsList.map { rev ->
      val value = rev.substringBefore(" ").toInt()
      val color = Color.valueOf(rev.substringAfter(" ").uppercase(Locale.getDefault()))
      color to value
    }
  }

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      var gameData = line.removePrefix("Game ")
      val gameIdx = gameData.substringBefore(":").toInt()
      gameData = gameData.substringAfter(": ")
      val isRevealPossible =
        gameData.split(";").none { reveal ->
          processReveal(reveal).any { (revealColor, revealValue) ->
            revealValue > (maxValues.find { it.first == revealColor }?.second ?: 0)
          }
        }
      if (isRevealPossible) gameIdx else 0
    }
  }

  fun part2(input: List<String>): Int {
    return input.sumOf { line ->
      val gameData = line.substringAfter(": ")
      var minValueRed = 0
      var minValueGreen = 0
      var minValueBlue = 0

      gameData.split(";").forEach { reveal ->
        processReveal(reveal).forEach { (revealColor, revealValue) ->
          when (revealColor) {
            Color.RED -> minValueRed = maxOf(minValueRed, revealValue)
            Color.GREEN -> minValueGreen = maxOf(minValueGreen, revealValue)
            Color.BLUE -> minValueBlue = maxOf(minValueBlue, revealValue)
          }
        }
      }
      minValueBlue * minValueGreen * minValueRed
    }
  }

  val testInput = readInput("Day02_test")
  check(part1(testInput) == 8)
  check(part2(testInput) == 2286)

  val input = readInput("Day02")
  part1(input).println()
  part2(input).println()
}
