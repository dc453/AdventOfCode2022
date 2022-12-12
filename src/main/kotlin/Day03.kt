import java.io.File

fun main() {

    val input = File("src/main/inputs/Day03.txt")
        .readText()
    val rucksackOrganizer = RucksackOrganizer(input)
    println("Day 3, part 1: ${rucksackOrganizer.getTotalPriorityScore()}")
    println("Day 3, part 2: ${rucksackOrganizer.getTotalGroupPriorityScore()}")

}

class Rucksack(val items: String) {
    val compartments: List<String> = listOf(
        items.substring(0 until items.length / 2),
        items.substring(items.length / 2 until items.length)
    )

    fun getDuplicateItem(): Char {
        val item = compartments[0].toSet() intersect compartments[1].toSet()
        return item.iterator()
            .next()
    }
}

class RucksackOrganizer(private val input: String) {
    val scoreHandler = RucksackItemScoreHandler()
    val rucksacks = createRucksacks()
    val elfGroups: List<Char> = rucksacks.windowed(3, 3)
        .map { group ->
            val groupItems = group.map {
                it.items.toList()
            }
            groupItems.reduce { acc, chars ->
                acc.intersect(chars.toSet()).toList()
            }[0]
        }

    private fun createRucksacks(): List<Rucksack> {
        return input.split("\n")
            .map { rucksackItems ->
                Rucksack(rucksackItems)
            }
    }

    fun getTotalPriorityScore(): Int {
        return rucksacks.sumOf {
            scoreHandler.getPriorityScore(it.getDuplicateItem())
        }
    }

    fun getTotalGroupPriorityScore(): Int {
        return elfGroups.sumOf { badge ->
            scoreHandler.getPriorityScore(badge)
        }
    }
}

class RucksackItemScoreHandler {
    private val itemPriorityValues: List<Char> = ('a'..'z')
        .plus('A' .. 'Z')
        .toList()

    fun getPriorityScore(item: Char): Int {
        return itemPriorityValues.indexOf(item) + 1
    }
}

