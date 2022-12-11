import java.io.File
import kotlin.math.floor

fun main() {

    val input = File("src/main/inputs/Day11.txt")
        .readText()

    val game1 = KeepAwayGame(input)
    for (i in 1..20) {
        game1.playRound()
    }
    println("Day 10, part 1: ${game1.monkeyBusinessLevel}")

}

class KeepAwayGame(input: String) {

    val monkeys = input.split("\n\n")
        .map { Monkey(it) }
    val monkeyBusinessLevel: Int
        get() {
            val mostActive = monkeys.sortedByDescending { it.numItemsInspected }
                .take(2)
                .map { it.numItemsInspected }
            return mostActive[0] * mostActive[1]
        }

    fun playRound() {
        monkeys.forEach { monkey ->
            val itemsToThrow = monkey.takeTurn()
            itemsToThrow.forEach { itemThrow ->
                monkeys[itemThrow.destination].items.add(itemThrow.item)
            }
        }
    }

}

data class ItemThrow(val item: Int, val destination: Int)

class Monkey(input: String) {

    private val info = input.split("\n")
        .map { it.trim() }
    val items: MutableList<Int> = info[1].split(": ")[1]
        .split(", ")
        .map { it.toInt() }
        .toMutableList()
    var numItemsInspected: Int = 0


    private val operation: List<String> = info[2].split(" ")
        .drop(3)
    private val testDivisibleNumber: Int = info[3].split(" ")
        .takeLast(1)[0]
        .toInt()
    private val testPassesDestination: Int = info[4].split(" ")
        .takeLast(1)[0]
        .toInt()
    private val testFailesDestination: Int = info[5].split(" ")
        .takeLast(1)[0]
        .toInt()

    fun getNewWorryLevel(currentWorryLevel: Int): Int {
        val formula = operation.map {
            when(it) {
                "old" -> currentWorryLevel.toString()
                else -> it
            }
        }
        val newWorryLevel = when(formula[1]) {
            "*" -> formula[0].toInt() * formula[2].toInt()
            "+" -> formula[0].toInt() + formula[2].toInt()
            else -> currentWorryLevel
        }.toDouble()
        return floor(newWorryLevel / 3).toInt()
    }

    fun getMonkeyToThrowTo(item: Int): Int {
        val worryLevel = getNewWorryLevel(item)
        return if (worryLevel % testDivisibleNumber == 0) {
            testPassesDestination
        } else {
            testFailesDestination
        }
    }

    fun takeTurn(): List<ItemThrow> {
        numItemsInspected += items.size
        val itemDestinations: List<ItemThrow> = items.map { item ->
            ItemThrow(getNewWorryLevel(item), getMonkeyToThrowTo(item))
        }
        items.clear()
        return itemDestinations
    }

}