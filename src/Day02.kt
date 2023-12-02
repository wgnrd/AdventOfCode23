import java.util.*

enum class Color {
  RED, GREEN, BLUE
}

fun main() {
  val maxValues = listOf(Color.RED to 12, Color.GREEN to 13, Color.BLUE to 14)
  fun processReveal(reveal: String): List<Pair<Color, Int>> {
    // reveal = 3 blue, 2 red
    val revealsList = reveal.trim().split(", ")
    return revealsList.map { rev ->
      val value = rev.substringBefore(" ").toInt()
      val color = Color.valueOf(rev.substringAfter(" ").uppercase(Locale.getDefault()))
      color to value
    }
  }

  fun part1(input: List<String>): Int {
    var sum = 0
    input.forEach { line ->
      var yeet = line.removePrefix("Game ")
      val gameIdx = yeet.substringBefore(":").toInt()
      yeet = yeet.substringAfter(": ")
      var isRevealPossible = true
      yeet.split(";").forEach {reveal ->
        processReveal(reveal).forEach { (revealColor, revealValue) ->
          if (revealValue > (maxValues.find { it.first == revealColor }?.second ?: 0))
            isRevealPossible = false
        }
      }
      if (isRevealPossible) {
        sum += gameIdx
      }
    }
    return sum
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  val testInput = readInput("Day02_test")
  check(part1(testInput) == 8)
  //  check(part2(testInput) == 281)

  val input = readInput("Day02")
  part1(input).println()
  part2(input).println()
}
