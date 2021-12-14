package advent.of.code.one

import advent.of.code.util.getInputFromResources

class SonarSweep {

    var input: List<Int> = getInputFromResources("one/input")
        .map { it.toInt() }

    fun part1() {
        println("Part 1:")

        val increasedMeasurementCount = input
            .filterIndexed { i, it ->
                i > 0 && it > input[i - 1]
            }
            .size
        println("An increase of depth has been measured $increasedMeasurementCount times.")
    }

    fun part1Fancy() {
        println("Part 1:")

        val increasedMeasurementCount = input
            .windowed(2)
            .filter {
                it[0] < it[1]
            }
            .size
        println("An increase of depth has been measured $increasedMeasurementCount times.")
    }

    fun part2() {
        println("Part 2:")
        val increasedMeasurementCount =
            input.filterIndexed() { i, _ ->
                input.hasNextWindow(i) && input.currentWindow(i) < input.nextWindow(i)

            }.size
        println("An increase of depth has been measured $increasedMeasurementCount times.")
    }

    fun part2Fancy() {
        println("Part 2:")
        val increasedMeasurementCount =
            input
                .windowed(3)
                .windowed(2)
                .filter {
                    it[0].sum() < it[1].sum()
                }
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