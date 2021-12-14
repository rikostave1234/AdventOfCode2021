package advent.of.code.two

import advent.of.code.util.getInputFromResources

class Navigation {
    private val steps = getInputFromResources("two/input").map {
        it.split(" ").toStep()
    }

    fun part1() {
        val finalPosition = navigatePart1()
        println("The submarine navigated to $finalPosition")
        println("The product of these coordinates is ${finalPosition.horizontal * finalPosition.depth}")
    }

    fun part2() {
        val finalPosition = navigatePart2()
        println("The submarine navigated to $finalPosition")
        println("The product of these coordinates is ${finalPosition.horizontal * finalPosition.depth}")
    }

    private fun navigatePart1(position: Position = Position(0, 0), index: Int = 0): Position {
        if (index == steps.size) return position

        val step = steps[index]
        val newPosition = when (step.direction) {
            Direction.FORWARD -> Position(
                horizontal = position.horizontal + step.amount,
                depth = position.depth
            )
            Direction.UP -> Position(
                horizontal = position.horizontal,
                depth = position.depth - step.amount
            )
            Direction.DOWN -> Position(
                horizontal = position.horizontal,
                depth = position.depth + step.amount
            )
        }
        return navigatePart1(newPosition, index+1)
    }

    private fun navigatePart2(position: Position = Position(0, 0, 0), index: Int = 0): Position {
        if (index == steps.size) return position

        val step = steps[index]
        val newPosition = when (step.direction) {
            Direction.FORWARD -> Position(
                horizontal = position.horizontal + step.amount,
                depth = position.depth + (position.aim * step.amount),
                aim = position.aim
            )
            Direction.UP -> Position(
                horizontal = position.horizontal,
                depth = position.depth,
                aim = position.aim - step.amount
            )
            Direction.DOWN -> Position(
                horizontal = position.horizontal,
                depth = position.depth,
                aim = position.aim + step.amount
            )
        }
        return navigatePart2(newPosition, index+1)
    }
}

data class Position(val horizontal: Int, val depth: Int, val aim: Int = 0)

data class Step(val direction: Direction, val amount: Int)

enum class Direction {
    FORWARD,
    UP,
    DOWN;
}

fun List<String>.toStep() =
    Step(this[0].toDirection(), this[1].toInt())

fun String.toDirection(): Direction =
    Direction.values().find {
        it.name.lowercase() == this } ?: error("Could not parse direction!")
