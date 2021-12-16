package advent.of.code

import advent.of.code.five.HydrothermalVenture
import advent.of.code.four.Bingo
import advent.of.code.one.SonarSweep
import advent.of.code.six.Lanternfish
import advent.of.code.three.BinaryDiagnostic
import advent.of.code.two.Navigation

fun main(args: Array<String>) {
    println("Welcome to AdventOfCode!")

    println("Day one:")
    SonarSweep().part1()
    SonarSweep().part2()
    SonarSweep().part1Fancy()
    SonarSweep().part2Fancy()

    println("Day two:")
    Navigation().part1()
    Navigation().part2()

    println("Day three:")
    BinaryDiagnostic().part1()
    BinaryDiagnostic().part2()

    println("Day four:")
    Bingo().part1()
    Bingo().part2()

    println("Day five:")
    HydrothermalVenture().part1()
    HydrothermalVenture().part2()

    println("Day 6:")
    Lanternfish().part1()
    Lanternfish().part2()
}