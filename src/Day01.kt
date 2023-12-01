fun main() {
  fun part1(input: List<String>): Int {
    var sum = 0
    input.forEach { line ->
      var firstNumber = 0
      var lastNumber = 0
      line.forEach { char ->
        if (char.isDigit()) {
          if (firstNumber == 0) {
            firstNumber = char.toString().toInt()
            lastNumber = char.toString().toInt()
          } else {
            lastNumber = char.toString().toInt()
          }
        }
        }
       sum += firstNumber * 10 + lastNumber
      }

    return sum
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  val testInput = readInput("Day01_test")
  check(part1(testInput) == 142)

  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
