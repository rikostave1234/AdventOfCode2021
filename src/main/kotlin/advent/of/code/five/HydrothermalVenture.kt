package advent.of.code.five

import advent.of.code.util.getInputFromResources

class HydrothermalVenture {
    private val input = getInputFromResources("five/input")

    //    val input: List<String> = """0,9 -> 5,9
//8,0 -> 0,8
//9,4 -> 3,4
//2,2 -> 2,1
//7,0 -> 7,4
//6,4 -> 2,0
//0,9 -> 2,9
//3,4 -> 1,4
//0,0 -> 8,8
//5,5 -> 8,2""".lines()
    val lines = input
        .map { it.split(" -> ") }
        .map { it.map { it.toCoordinates() } }
        .map { Line(it.first(), it.last()) }

    fun part1() {
        val points = lines
            .filter { it.start.x == it.end.x || it.start.y == it.end.y }
            .flatMap { it.points() }

        val pointsWithOverlappingLines = points.groupingBy { it }.eachCount().filter { it.value > 1 }
        println("Part 1:")
        println("Lines are overlapping at ${pointsWithOverlappingLines.size} points")
    }

    fun part2() {
        val points = lines
            .flatMap { it.points() }

        val pointsWithOverlappingLines = points.groupingBy { it }.eachCount().filter { it.value > 1 }
        println("Part 2")
        println("Lines are overlapping at ${pointsWithOverlappingLines.size} points")
    }
}

data class Coordinates(val x: Int, val y: Int)

data class Line(val start: Coordinates, val end: Coordinates) {
    fun points(): List<Coordinates> =
        if (start.x == end.x)
            range(start.y, end.y).map { y -> Coordinates(start.x, y) }
        else if (start.y == end.y)
            range(start.x, end.x).map { x -> Coordinates(x, start.y) }
        else {
            val xRange = range(start.x, end.x).toList()
            range(start.y, end.y).mapIndexed { i, it ->
                Coordinates(xRange[i], it)
            }
        }
}

fun range(start: Int, end: Int) = if (start > end) start.downTo(end) else (start..end)

fun String.toCoordinates(): Coordinates {
    val splitted = this.split(",")
    return Coordinates(splitted[0].toInt(), splitted[1].toInt())
}