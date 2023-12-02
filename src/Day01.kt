fun main() {
  fun processLine(line: String): Int {
    val digits = line.filter(Char::isDigit)
    return if (digits.isNotEmpty()) {
      val firstNumber = digits.first().toString().toInt()
      val lastNumber = digits.last().toString().toInt()
      firstNumber * 10 + lastNumber
    } else {
      0
    }
  }

  fun replaceLiteralNumbers(input: String): String {
    val replacements = mapOf(
      "one" to "on1e",
      "two" to "t2wo",
      "three" to "th3ree",
      "four" to "fo4ur",
      "five" to "fi5ve",
      "six" to "si6x",
      "seven" to "sev7en",
      "eight" to "ei8ght",
      "nine" to "ni9ne"
    )

    var output = input
    replacements.forEach { (word, digit) ->
      output = output.replace(word, digit, ignoreCase = true)
    }
    return output
  }

  fun part1(input: List<String>): Int {
    return input.sumOf { line -> processLine(line) }
  }

  fun part2(input: List<String>): Int {
    return input.sumOf { line -> processLine(replaceLiteralNumbers(line)) }
  }

  val testInput = readInput("Day01_test")
  //  check(part1(testInput) == 142)
  check(part2(testInput) == 281)

  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
