package advent.of.code.three

import advent.of.code.util.getInputFromResources

class BinaryDiagnostic {

    private val input = getInputFromResources("three/input")

    fun part1() {
        val gamma = List(input[0].length) { index ->
            input.map { it[index] }
        }
            .map { mostCommonBit(it) }
            .joinToString(separator = "") { it }.toInt(radix = 2)

        val epsilon = gamma.toString(radix = 2).map {
            if (it == '0')
                "1"
            else
                "0"
        }.joinToString(separator = "") { it }.toInt(radix = 2)

        println("gamma rate: $gamma; epsilon rate: $epsilon")
        println("power consumption: ${gamma * epsilon}")
    }

    fun mostCommonBit(bits: List<Char>): String =
        if (bits.filter { bit -> bit == '1' }.size < bits.filter { bit -> bit == '0' }.size)
            "0"
        else
            "1"

    fun leastCommonBit(bits: List<Char>): String =
        if (bits.filter { bit -> bit == '1' }.size < bits.filter { bit -> bit == '0' }.size)
            "1"
        else
            "0"

    fun part2() {
        val oxygenRating = rating(input, {mostCommonBit(it)})
        val co2Rating = rating(input, {leastCommonBit(it)})

        println("oxygen generator rating: $oxygenRating; co2 scrubber rating: $co2Rating")
        println("life support rating: ${oxygenRating * co2Rating}")
    }

    fun rating(numbers: List<String>, bitIdentifier: (bits: List<Char>) -> String, index: Int = 0): Int {
        if (numbers.size == 1) return numbers.first().toInt(radix = 2)

        val bitsPerPosition = List(numbers[0].length) { i ->
            numbers.map { it[i] }
        }
        val bitToKeep = bitIdentifier(bitsPerPosition[index])
        val remainingNumbers = numbers.filter { it[index].toString() == bitToKeep }
        return rating(remainingNumbers, bitIdentifier, index+1)
    }
}