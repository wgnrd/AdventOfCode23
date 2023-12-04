fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      val cardData = line.substringAfter(": ")
      val winningNumbers =
        cardData.substringBefore(" | ").split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
      val cardNumbers =
        cardData.substringAfter(" | ").split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

      cardNumbers
        .fold(0) { cardValue, number ->
          if (number in winningNumbers) {
            if (cardValue == 0) cardValue + 1 else cardValue * 2
          } else {
            cardValue
          }
        }
        .toInt()
    }
  }

  fun part2(input: List<String>): Int {
    val scratchCardsInstances = input.indices.associateWith { 1 }.toMutableMap()
    var amountOfWinningNumbers = 0
    input.forEachIndexed { idx, line ->
      amountOfWinningNumbers = 0
      val cardData = line.substringAfter(": ")
      val winningNumbers =
        cardData.substringBefore(" | ").split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
      val cardNumbers =
        cardData.substringAfter(" | ").split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

      cardNumbers.forEach { number ->
        if (number in winningNumbers) {
          amountOfWinningNumbers++
        }
      }
      for (i in idx until idx +amountOfWinningNumbers) {
        scratchCardsInstances[i+1] = scratchCardsInstances[i+1]!! + scratchCardsInstances[idx]!!
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
