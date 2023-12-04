fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      var cardData = line.substringAfter(": ")
      var winningNumbers = cardData.substringBefore(" | ")
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

      var cardNumbers = cardData.substringAfter(" | ")
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

      var valueOfCard = 0
      for (element in cardNumbers) {
        if (element in winningNumbers) {
          if (valueOfCard == 0)
            valueOfCard += 1
          else valueOfCard *= 2
        }
      }


      valueOfCard
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
