fun main() {
  fun isSymbol(char: Char): Boolean {
    return !char.isDigit() && char != '.'
  }

  fun hasAdjacentSymbol(map: List<String>, row: Int, col: Int): Boolean {
    return (row > 0 && isSymbol(map[row - 1][col])) || // left
        (row < map.size - 1 && isSymbol(map[row + 1][col])) || // right
        (col > 0 && isSymbol(map[row][col - 1])) || // up
        (col < map[row].length - 1 && isSymbol(map[row][col + 1])) || // down
        (row > 0 && col > 0 && isSymbol(map[row - 1][col - 1])) || // diagonal up-left
        (row > 0 && col < map[row].length - 1 && isSymbol(map[row - 1][col + 1])) || // diagonal up-right
        (row < map.size - 1 && col > 0 && isSymbol(map[row + 1][col - 1])) || // diagonal down-left
        (row < map.size - 1 && col < map[row].length - 1 && isSymbol(map[row + 1][col + 1])) // diagonal down-right
  }

  fun part1(input: List<String>): Int {
    var currentPartNumber = 0
    var sumPartNumbers = 0
    var hasSymbolAdjacent = false

    input.mapIndexed { rowIndex, line ->
      line.mapIndexed { colIndex, char ->
        if (char.isDigit()) {
          currentPartNumber = if (currentPartNumber == 0)
            char.toString().toInt()
          else
            currentPartNumber * 10 + char.toString().toInt()

          if (hasAdjacentSymbol(input, rowIndex, colIndex)) {
            hasSymbolAdjacent = true
          }
        } else {
          if (hasSymbolAdjacent) {
            sumPartNumbers += currentPartNumber
          }
          currentPartNumber = 0
          hasSymbolAdjacent = false
        }
      }
      if (hasSymbolAdjacent) {
        sumPartNumbers += currentPartNumber
      }
      currentPartNumber = 0
      hasSymbolAdjacent = false
    }
    return sumPartNumbers
  }

  fun part2(input: List<String>): Int {
    return input.size
  }

  val testInput = readInput("Day03_test")
  val testInput2 = readInput("Day03_test2")
  check(part1(testInput) == 4361)
  check(part1(testInput2) == 925)
  //  check(part2(testInput) == 2286)

  val input = readInput("Day03")
//  part1(input).println()
//  part2(input).println()
}
