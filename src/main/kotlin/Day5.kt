import java.io.File

fun main() {

    val input = File("src/main/inputs/Day5.txt")
        .readText()

    val cargoCrane = CargoCrane(input)
    cargoCrane.arrangeStacks()
    println("Day 5, part 1: ${cargoCrane.getTopCrates()}")

    val cargoCrane9001 = CargoCrane(input, true)
    cargoCrane9001.arrangeStacks()
    println("Day 5, part 2: ${cargoCrane9001.getTopCrates()}")

}

class CargoCrane(input: String, private val enableMultipleCrateMode: Boolean = false) {
    private val inputParts = input.split("\n\n")
    private val rawSupplyStacksData = inputParts[0]
        .split("\n")
    private val startingSupplyStacks = rawSupplyStacksData.dropLast(1)
    private val stackIndexes = rawSupplyStacksData.takeLast(1)[0]

    val supplyStacks: MutableList<MutableList<String>> = mutableListOf()
    val instructions: List<List<Int>> = inputParts[1]
        .split("\n")
        .map {
            val parts = it.split(" ")
            listOf(parts[1].toInt(), parts[3].toInt(), parts[5].toInt())
        }

    init {
        assignStartingSupplyStacks()
    }

    private fun assignStartingSupplyStacks() {
        columnLoop@ for (i in 1..9) {
            val search = i.toString()
            val stackIndex = stackIndexes.indexOf(search)
            if (stackIndex == -1) {
                continue@columnLoop
            }
            supplyStacksLoop@ for (stack in startingSupplyStacks) {
                if (stack[stackIndex] == ' ' || stack[stackIndex] == '[' || stack[stackIndex] == ']') {
                    continue@supplyStacksLoop
                }
                if (supplyStacks.size < i) {
                    supplyStacks.add(mutableListOf())
                }
                supplyStacks[i - 1].add(stack[stackIndex].toString())
            }
        }
        supplyStacks.map { it.reverse() }
    }

    fun arrangeStacks() {
        instructions.forEach { instruction ->
            if (enableMultipleCrateMode) {
                moveMultipleCrates(instruction)
            } else {
                moveCrate(instruction)
            }
        }
    }

    private fun moveMultipleCrates(instruction: List<Int>) {
        val crates = supplyStacks[instruction[1] - 1].takeLast(instruction[0])
        supplyStacks[instruction[2] - 1].addAll(crates)
        supplyStacks[instruction[1] - 1] = supplyStacks[instruction[1] - 1]
            .dropLast(instruction[0])
            .toMutableList()
    }

    private fun moveCrate(instruction: List<Int>) {
        for (i in 1..instruction[0]) {
            val crate = supplyStacks[instruction[1] - 1].removeLast()
            supplyStacks[instruction[2] - 1].add(crate)
        }
    }

    fun getTopCrates(): String {
        return supplyStacks.joinToString("") { stack ->
            stack.last().toString()
        }
    }

}