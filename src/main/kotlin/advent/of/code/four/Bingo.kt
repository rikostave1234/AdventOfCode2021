package advent.of.code.four

import advent.of.code.util.getInputFromResources

class Bingo {
    private val input = getInputFromResources("four/input")
    private val numbers = input[0].split(",").map { it.toInt() }
    private val boards = input
        .drop(1)
        .filter { it.isNotBlank() }
        .chunked(5)
        .map { it.toBoard() }

    fun part1(boards: List<Board> = this.boards, index: Int = 0) {
        val updatedBoards = boards.map { it.apply(numbers[index]) }
        updatedBoards.find { it.isWinner() }?.let {
            val score = it.score(numbers[index])
            println("Bingo!!! First winning board's score is $score")
        } ?: run {
            part1(updatedBoards, index + 1)
        }
    }

    fun part2(boards: List<Board> = this.boards, lastWinnerScore: Int = 0, index: Int = 0) {
        if (index == numbers.size || boards.isEmpty()) {
            println("Bingo!!! Last winning board's score is $lastWinnerScore")
        } else {
            val updatedBoards = boards.map { it.apply(numbers[index]) }
            val winners = updatedBoards.filter { it.isWinner() }
            part2(
                updatedBoards - winners.toSet(),
                winners.lastOrNull()?.score(numbers[index]) ?: lastWinnerScore,
                index + 1
            )
        }
    }
}

data class Field(val value: Int, val marked: Boolean = false)

class Board(private val rows: List<List<Field>>) {
    private fun columns() = List(5) { i -> rows.map { it[i] } }
    fun isWinner(): Boolean {
        val bingoInRows = rows.any { it.all { field -> field.marked } }
        val bingoInColumns = columns().any { it.all { field -> field.marked } }
        return bingoInRows || bingoInColumns
    }

    fun score(factor: Int) = rows.flatten().filter { !it.marked }.sumOf { it.value } * factor
    fun apply(number: Int) = Board(
        rows.map { it.map { field -> if (field.value == number) field.copy(marked = true) else field } }
    )
}

fun List<String>.toBoard(): Board {
    return Board(this.map { y ->
        y.trim().split("\\s+".toRegex()).map { x ->
            Field(x.toInt())
        }
    })
}