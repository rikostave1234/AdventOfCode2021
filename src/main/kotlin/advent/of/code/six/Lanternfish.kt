package advent.of.code.six

import advent.of.code.util.getInputFromResources
import java.util.Collections
import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToLong

class Lanternfish {

    val fishies = getInputFromResources("six/input").first()
        .split(",").map { Fish(it.toInt()) }

    fun part1(fishies: List<Fish> = this.fishies, count: Int = 0) {
        if (count == 80) {
            println("After 80 days, there is a total of ${fishies.size} lanternfish")
        } else {
            part1(fishies.flatMap { it.aDayHasPassed() }, count + 1)
        }
    }

    fun part2() {
        val buckets = MutableList(9) { 0L }
        this.fishies.map { it.daysLeft }.groupingBy { it }.eachCount().forEach { index, count ->
            buckets[index] = count.toLong()
        }
        repeat(256) {
            Collections.rotate(buckets, -1)
            buckets[6] += buckets[8]
        }
        println("After 256 days, there is a total of ${buckets.sum()} fishies")

    }
}

class Fish(val daysLeft: Int) {
    fun aDayHasPassed(): List<Fish> =
        if (daysLeft == 0)
            listOf(Fish(8), Fish(6))
        else
            listOf(Fish(daysLeft - 1))
}