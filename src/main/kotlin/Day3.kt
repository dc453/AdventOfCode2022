import java.io.File

fun main() {

    val input = File("src/main/inputs/Day3.txt")
        .readText()
    val rucksackOrganizer = RucksackOrganizer(input)
    println("Day 3, part 1: ${rucksackOrganizer.getTotalPriorityScore()}")

}

class Rucksack(items: String) {
    private val itemPriorityValues: List<Char> = ('a'..'z')
        .plus('A' .. 'Z')
        .toList()

    val compartments: List<String> = listOf(
        items.substring(0 until items.length / 2),
        items.substring(items.length / 2 until items.length)
    )

    fun getDuplicateItem(): Char {
        val item = compartments[0].toSet() intersect compartments[1].toSet()
        return item.iterator()
            .next()
    }

    fun getPriorityScore(): Int {
        return itemPriorityValues.indexOf(getDuplicateItem()) + 1
    }
}

class RucksackOrganizer(val input: String) {
    val rucksacks = createRucksacks()

    private fun createRucksacks(): List<Rucksack> {
        return input.split("\n")
            .map { rucksackItems ->
                Rucksack(rucksackItems)
            }
    }

    fun getTotalPriorityScore(): Int {
        return rucksacks.sumOf { it.getPriorityScore() }
    }
}

