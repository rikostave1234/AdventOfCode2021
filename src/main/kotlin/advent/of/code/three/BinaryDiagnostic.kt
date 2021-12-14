package advent.of.code.three

import advent.of.code.util.getInputFromResources

class BinaryDiagnostic {

    private val input = getInputFromResources("three/input")

    fun part1() {
        val gamma = List(input[0].length) { index ->
            input.map { it[index] }
        }.map { bitsPerPosition ->
            if (bitsPerPosition.filter { bit -> bit == '1' }.size > bitsPerPosition.filter { bit -> bit == '0' }.size)
                "1"
            else
                "0"
        }.joinToString(separator = "") { it }.toInt(radix = 2)

        val epsilon = gamma.toString(radix = 2).map {
            if (it == '0')
                "1"
            else
                "0"
        }.joinToString(separator = "") { it }.toInt(radix = 2)

        println("gamma rate: $gamma; epsilon rate: $epsilon")
        println("power consumption: ${gamma * epsilon}")
    }

}