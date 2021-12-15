package advent.of.code

import advent.of.code.one.SonarSweep
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
}