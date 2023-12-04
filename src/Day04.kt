fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val cardData = line.substringAfter(": ")
      val winningNumbers = cardData
        .substringBefore(" | ")
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
      val cardNumbers = cardData
        .substringAfter(" | ")
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

      cardNumbers.fold(0) { cardValue, number ->
        if (number in winningNumbers) {
          if (cardValue == 0) cardValue + 1 else cardValue * 2
        } else {
          cardValue
        }
      }.toInt()
    }
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  val testInput = readInput("Day04_test")
  check(part1(testInput) == 13)
  //  check(part2(testInput) == 2286)

  val input = readInput("Day04")
  part1(input).println()
  //    part2(input).println()
}
