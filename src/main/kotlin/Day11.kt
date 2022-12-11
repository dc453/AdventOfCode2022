import kotlin.math.floor

fun main() {

}

class KeepAwayGame(input: String) {

    val monkeys = input.split("\n\n")
        .map { Monkey(it) }

}

class Monkey(input: String) {

    private val info = input.split("\n")
        .map { it.trim() }
    val items: List<Int> = info[1].split(": ")[1]
        .split(", ")
        .map { it.toInt() }
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

    fun getMonkeyToThrowTo(): Int {
        val worryLevel = getNewWorryLevel(items[0])
        return if (worryLevel % testDivisibleNumber == 0) {
            testPassesDestination
        } else {
            testFailesDestination
        }
    }

}