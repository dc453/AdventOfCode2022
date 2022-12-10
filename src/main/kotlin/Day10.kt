import java.io.File

fun main() {

    val input = File("src/main/inputs/Day10.txt")
        .readText()
    val vs = VideoSystem()

    vs.draw(input)
    println("Day 10, part 1: ${vs.getSignalStrengthTotal(listOf(20, 60, 100, 140, 180, 220))}")
    println("Day 10, part 2:")
    println(vs.getDisplay())

}

class VideoSystem() {

    private val display: MutableList<String> = mutableListOf()

    var cycle: Int = 0
        set(value) {
            addPixelToDisplay()
            field = value
            recordSignalStrength()
        }

    private fun addPixelToDisplay() {
        val pixel = if (x - 1 <= cycle % 40 && cycle % 40 <= x + 1) {
            "#"
        } else {
            "."
        }
        display.add(pixel)
    }

    var x: Int = 1
    val signalStrengths: MutableList<SignalStrength> = mutableListOf()

    private fun recordSignalStrength() {
        if (cycle % 20 == 0) {
            val newSignal = SignalStrength(cycle, x)
            signalStrengths.add(newSignal)
        }
    }

    fun draw(instructions: String) {
        instructions.split("\n")
            .forEach { instruction ->
                when (instruction) {
                    "noop" -> {
                        cycle += 1
                    }

                    else -> {
                        val (_, _x) = instruction.split(" ")
                        cycle += 1
                        cycle += 1
                        x += _x.toInt()
                    }
                }
            }
    }

    fun getSignalStrengthTotal(cycles: List<Int>): Int {
        return signalStrengths.filter { cycles.contains(it.cycle) }
            .sumOf { it.strength }
    }

    fun getDisplay(): String {
        return display
            .joinToString("")
            .windowed(40, 40)
            .joinToString("\n")
    }

}

class SignalStrength(val cycle: Int, private val x: Int) {
    val strength
        get() = cycle * x
}