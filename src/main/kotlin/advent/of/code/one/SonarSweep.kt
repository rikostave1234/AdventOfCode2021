package advent.of.code.one

import advent.of.code.one.DepthMeasurement.DECREASED
import advent.of.code.one.DepthMeasurement.INCREASED
import advent.of.code.one.DepthMeasurement.NA
import advent.of.code.one.DepthMeasurement.NO_CHANGE
import advent.of.code.util.getInputFromResources

class SonarSweep {

    var input: List<Int> = getInputFromResources("one/input")
        .map { it.toInt() }

    fun part1() {
        println("Part 1:")

        val increasedMeasurementCount = input
            .mapIndexed { i, it ->
                when {
                    i == 0 -> NA
                    it > input[i - 1] -> INCREASED
                    else -> DECREASED
                }
            }
            .filter { it == INCREASED }
            .size
        println("An increase of depth has been measured $increasedMeasurementCount times.")
    }

    fun part2() {
        println("Part 2:")
        val increasedMeasurementCount = List(input.size) { i ->
            when {
                !input.hasNextWindow(i) -> NA
                input.currentWindow(i) < input.nextWindow(i) -> INCREASED
                input.currentWindow(i) == input.nextWindow(i) -> NO_CHANGE
                else -> DECREASED
            }
        }
            .filter { it == INCREASED }
            .size
        println("An increase of depth has been measured $increasedMeasurementCount times.")
    }
}

fun List<Int>.hasNextWindow(index: Int) =
    index < this.size - 3

fun List<Int>.currentWindow(index: Int) =
    (this[index] + this[index + 1] + this[index + 2])

fun List<Int>.nextWindow(index: Int) =
    (this[index + 1] + this[index + 2] + this[index + 3])

enum class DepthMeasurement {
    INCREASED,
    DECREASED,
    NO_CHANGE,
    NA;
}