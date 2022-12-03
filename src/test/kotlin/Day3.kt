import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3_Tests {

    @Test
    fun createRucksacks_shouldDetermineItemsInEachCompartment() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                "PmmdzqPrVvPwwTWBwg\n" +
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw"

        val rucksacks = RucksackOrganizer(input).rucksacks

        assertEquals("vJrwpWtwJgWr", rucksacks[0].compartments[0])
        assertEquals("hcsFMMfFFhFp", rucksacks[0].compartments[1])
        assertEquals("jqHRNqRjqzjGDLGL", rucksacks[1].compartments[0])
        assertEquals("rsFMfFZSrLrFZsSL", rucksacks[1].compartments[1])
    }

    @Test
    fun Rucksack_shouldDetermineWhichItemAppearsInBothCompartments() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp"
        val rucksack = Rucksack(input)

        val duplicateItem = rucksack.getDuplicateItem()

        assertEquals('p', duplicateItem)
    }

    @Test
    fun Rucksack_shouldCalculateDuplicateItemScore() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp"
        val rucksack = Rucksack(input)

        val priorityScore = rucksack.getPriorityScore()

        assertEquals(16, priorityScore)
    }

    @Test
    fun RucksackOrganizer_shouldCalculateAllItemScores() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                "PmmdzqPrVvPwwTWBwg\n" +
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw"

        val rucksackOrganizer = RucksackOrganizer(input)

        assertEquals(157, rucksackOrganizer.getTotalPriorityScore())
    }

}