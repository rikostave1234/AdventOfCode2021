package advent.of.code.four

import advent.of.code.util.getInputFromResources
import java.util.UUID

class Bingo {
    val input = getInputFromResources("four/input")
    private val numbers = input[0].split(",").map { it.toInt() }
    private val boards = input
        .drop(1)
        .filter { it.isNotBlank() }
        .chunked(5)
        .map { it.toBoard() }

    fun part1(index: Int = 0) {
        boards.forEach { it.markFields(numbers[index]) }

        boards.find { it.isWinner() }?.let {
            val score = it.score() * numbers[index]
            println("Bingo!!! First winning board's score is $score")
        } ?: run {
            part1(index + 1)
        }
    }

    fun part2(index: Int = 0, knownWinners: List<Pair<String, Int>> = emptyList()) {
        if (index == numbers.size || boards.size == knownWinners.size) {
            println("Bingo!!! Last winning board's score is ${knownWinners.last().second}")
        } else {
            boards.forEach { it.markFields(numbers[index]) }
            part2(
                index + 1,
                knownWinners + boards
                    .filter { !knownWinners.map { it.first }.contains(it.id) }
                    .filter { it.isWinner() }
                    .map { it.id to it.score() * numbers[index] }
            )
        }
    }
}

data class Field(val value: Int, var marked: Boolean = false) {
    fun mark() {
        this.marked = true
    }
}

data class Board(val rows: List<List<Field>>, val id: String = UUID.randomUUID().toString()) {
    fun markFields(number: Int) {
        for (field in fields()) {
            if (field.value == number) field.mark()
        }
    }
    fun columns() = List(5) { i -> rows.map { it[i] } }
    fun fields() = rows.flatten()
    fun isWinner(): Boolean {
        val bingoInRows = rows.any { it.all { field -> field.marked } }
        val bingoInColumns = columns().any { it.all { field -> field.marked } }
        return bingoInRows || bingoInColumns
    }
    fun score() = this.fields().filter { !it.marked }.sumOf { it.value }
}

fun List<String>.toBoard(): Board {
    return Board(this.map { y ->
        y.trim().split("\\s+".toRegex()).map { x ->
            Field(x.toInt())
        }
    })
}