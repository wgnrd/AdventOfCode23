fun main() {
  fun parseCardData(line: String): Pair<List<Int>, List<Int>> {
    val cardData = line.substringAfter(": ")
    val winningNumbers = cardData.substringBefore(" | ")
      .split(" ")
      .filter { it.isNotEmpty() }
      .map { it.toInt() }
    val cardNumbers = cardData.substringAfter(" | ")
      .split(" ")
      .filter { it.isNotEmpty() }
      .map { it.toInt() }
    return winningNumbers to cardNumbers
  }

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val (winningNumbers, cardNumbers) = parseCardData(line)
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
    val scratchCardsInstances = input.indices.associateWith { 1 }.toMutableMap()
    input.forEachIndexed { idx, line ->
      val (winningNumbers, cardNumbers) = parseCardData(line)
      val amountOfWinningNumbers = cardNumbers.count { it in winningNumbers }
      for (i in idx until idx + amountOfWinningNumbers) {
        scratchCardsInstances[i + 1] = scratchCardsInstances[i + 1]!! + scratchCardsInstances[idx]!!
      }
    }
    return scratchCardsInstances.values.sum()
  }

  val testInput = readInput("Day04_test")
  check(part1(testInput) == 13)
  check(part2(testInput) == 30)

  val input = readInput("Day04")
  part1(input).println()
  part2(input).println()
}
