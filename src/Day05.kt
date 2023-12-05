import kotlin.math.max
import kotlin.math.min

fun main() {
  fun getMapData(input: List<String>, startIndex: Int): List<Triple<Long, Long, Long>> {
    val returnMap = mutableListOf<Triple<Long, Long, Long>>()
    var i = startIndex
    do {
      i++
      val line = input[i]
      val (destinationStart, sourceStart, range) = line.split(" ").map { it.toLong() }
      returnMap.add(Triple(destinationStart, sourceStart, range))
    } while (i + 1 < input.size && input[(i + 1)].isNotBlank())
    return returnMap.toList()
  }

  fun convertUsingMap(input: List<Long>, conversionMap: List<Triple<Long, Long, Long>>): List<Long> {
    val returnList = mutableListOf<Long>()
    input.forEach { inputValue ->
      val result = conversionMap.firstNotNullOfOrNull { (destinationStart, sourceStart, range) ->
        if (inputValue in sourceStart..(sourceStart + range)) {
          inputValue - (sourceStart - destinationStart)
        } else {
          null // Return null if condition is not met
        }
      } ?: inputValue // Use inputValue if none of the conditions match
      returnList.add(result)
    }
    return returnList.toList()
  }

  fun innerJoinedRange(range1: LongRange, range2: LongRange): LongRange? {
    val start = max(range1.first, range2.first)
    val end = min(range1.last, range2.last)
    return if (start <= end) start..end else null
  }

  fun converRangesUsingMap(input: List<LongRange>, conversionMap: List<Triple<Long, Long, Long>>): List<LongRange> {
    val returnList = mutableListOf<LongRange>()
    input.forEach { inputValue ->
      val result = conversionMap.firstNotNullOfOrNull { (destinationStart, sourceStart, range) ->
        val sourceRange = sourceStart..(sourceStart + range)
        // get the intersection of the two ranges
        // if the inputValue is bigger than the sourceRange, we need to return two different ranges
        // also make sure that both ranges are converted correctly using all of the conversionMap
        // this is TBD!
        innerJoinedRange(inputValue, sourceRange)?.let {
          it.first - (sourceStart - destinationStart)..it.last - (sourceStart - destinationStart)
        }
      } ?: inputValue // Use inputValue if none of the conditions match
      returnList.add(result)
    }
    return returnList.toList()
  }

  fun part1(input: List<String>): Long {
    val seeds = input[0].substringAfter(": ").split(" ").map { it.toLong() }

    val seedToSoilMapStart = input.indexOfFirst { it.startsWith("seed-to-soil") }
    val seedToSoilMap = getMapData(input, seedToSoilMapStart)
    val soils = convertUsingMap(seeds, seedToSoilMap)

    val soilToFertilizerMapStart = input.indexOfFirst { it.startsWith("soil-to-fertilizer") }
    val soilToFertilizerMap = getMapData(input, soilToFertilizerMapStart)
    val fertilizers = convertUsingMap(soils, soilToFertilizerMap)

    val fertilizerToWaterMapStart = input.indexOfFirst { it.startsWith("fertilizer-to-water") }
    val fertilizerToWaterMap = getMapData(input, fertilizerToWaterMapStart)
    val waters = convertUsingMap(fertilizers, fertilizerToWaterMap)

    val waterToLightMapStart = input.indexOfFirst { it.startsWith("water-to-light") }
    val waterToLightMap = getMapData(input, waterToLightMapStart)
    val lights = convertUsingMap(waters, waterToLightMap)

    val lightToTemperatureMapStart = input.indexOfFirst { it.startsWith("light-to-temperature") }
    val lightToTemperatureMap = getMapData(input, lightToTemperatureMapStart)
    val temperatures = convertUsingMap(lights, lightToTemperatureMap)

    val temperatureToHumidityMapStart = input.indexOfFirst { it.startsWith("temperature-to-humidity") }
    val temperatureToHumidityMap = getMapData(input, temperatureToHumidityMapStart)
    val humidities = convertUsingMap(temperatures, temperatureToHumidityMap)

    val humidityToLocationMapStart = input.indexOfFirst { it.startsWith("humidity-to-location") }
    val humidityToLocationMap = getMapData(input, humidityToLocationMapStart)
    val locations = convertUsingMap(humidities, humidityToLocationMap)

    return locations.min()
  }

  fun part2(input: List<String>): Long {
    val seedRanges = input[0].substringAfter(": ").split(" ").map { it.toLong() }
    val seeds: MutableList<LongRange> = mutableListOf()
    for (i in seedRanges.indices step 2) {
      seeds.add(seedRanges[i]..seedRanges[i] + seedRanges[i + 1])
    }

    val seedToSoilMapStart = input.indexOfFirst { it.startsWith("seed-to-soil") }
    val seedToSoilMap = getMapData(input, seedToSoilMapStart)
    val soils = converRangesUsingMap(seeds, seedToSoilMap)

    val soilToFertilizerMapStart = input.indexOfFirst { it.startsWith("soil-to-fertilizer") }
    val soilToFertilizerMap = getMapData(input, soilToFertilizerMapStart)
    val fertilizers = converRangesUsingMap(soils, soilToFertilizerMap)

    val fertilizerToWaterMapStart = input.indexOfFirst { it.startsWith("fertilizer-to-water") }
    val fertilizerToWaterMap = getMapData(input, fertilizerToWaterMapStart)
    val waters = converRangesUsingMap(fertilizers, fertilizerToWaterMap)

    val waterToLightMapStart = input.indexOfFirst { it.startsWith("water-to-light") }
    val waterToLightMap = getMapData(input, waterToLightMapStart)
    val lights = converRangesUsingMap(waters, waterToLightMap)

    val lightToTemperatureMapStart = input.indexOfFirst { it.startsWith("light-to-temperature") }
    val lightToTemperatureMap = getMapData(input, lightToTemperatureMapStart)
    val temperatures = converRangesUsingMap(lights, lightToTemperatureMap)

    val temperatureToHumidityMapStart = input.indexOfFirst { it.startsWith("temperature-to-humidity") }
    val temperatureToHumidityMap = getMapData(input, temperatureToHumidityMapStart)
    val humidities = converRangesUsingMap(temperatures, temperatureToHumidityMap)

    val humidityToLocationMapStart = input.indexOfFirst { it.startsWith("humidity-to-location") }
    val humidityToLocationMap = getMapData(input, humidityToLocationMapStart)
    val locations = converRangesUsingMap(humidities, humidityToLocationMap)

    return locations.minByOrNull { it.last - it.first }!!.first
  }

  val testInput = readInput("Day05_test")
  check(part1(testInput) == 35.toLong())
//  check(part2(testInput) == 46.toLong())

  val input = readInput("Day05")
  part1(input).println()
  part2(input).println() // not working yet
}
