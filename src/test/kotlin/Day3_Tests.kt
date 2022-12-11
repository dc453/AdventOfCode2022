import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3_Tests {

    var input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw"

    @Test
    fun createRucksacks_shouldDetermineItemsInEachCompartment() {
        val rucksacks = RucksackOrganizer(input).rucksacks

        assertEquals("vJrwpWtwJgWr", rucksacks[0].compartments[0])
        assertEquals("hcsFMMfFFhFp", rucksacks[0].compartments[1])
        assertEquals("jqHRNqRjqzjGDLGL", rucksacks[1].compartments[0])
        assertEquals("rsFMfFZSrLrFZsSL", rucksacks[1].compartments[1])
    }

    @Test
    fun Rucksack_shouldDetermineWhichItemAppearsInBothCompartments() {
        input = "vJrwpWtwJgWrhcsFMMfFFhFp"
        val rucksack = Rucksack(input)

        val duplicateItem = rucksack.getDuplicateItem()

        assertEquals('p', duplicateItem)
    }

    @Test
    fun RucksackOrganizer_shouldCalculateAllItemScores() {
        val rucksackOrganizer = RucksackOrganizer(input)
        val totalPriorityScore = rucksackOrganizer.getTotalPriorityScore()
        assertEquals(157, totalPriorityScore)
    }

    @Test
    fun RucksackOrganizer_shouldDetermineElfGroupBadges() {
        val rucksackOrganizer = RucksackOrganizer(input)
        val elfGroups = rucksackOrganizer.elfGroups
        assertEquals(listOf('r', 'Z'), elfGroups)
    }

    @Test
    fun RucksackOrganizer_shouldCalculateTotalGroupItemScores() {
        val rucksackOrganizer = RucksackOrganizer(input)
        val totalGroupPriorityScore = rucksackOrganizer.getTotalGroupPriorityScore()
        assertEquals(70, totalGroupPriorityScore)
    }

}