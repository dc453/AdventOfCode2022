import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04_Tests {

    val input = "2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8"

    @Test
    fun CleanupScheduler_shouldDetermineAssignments() {
        val cleanupScheduler = CleanupScheduler(input)
        val group1Assignments: List<List<Int>> = cleanupScheduler.groups[0]
        val group2Assignments: List<List<Int>> = cleanupScheduler.groups[1]

        assertEquals(listOf(2, 3, 4), group1Assignments[0])
        assertEquals(listOf(6, 7, 8), group1Assignments[1])
        assertEquals(listOf(2, 3), group2Assignments[0])
        assertEquals(listOf(4, 5), group2Assignments[1])
    }

    @Test
    fun CleanupScheduler_shouldIdentifyWhenAssignment1FullyContainsAssignment2() {
        val cleanupScheduler = CleanupScheduler(input)
        val result = cleanupScheduler.checkGroupForFullyContainedAssignments(cleanupScheduler.groups[3])
        assertEquals(true, result)
    }

    @Test
    fun CleanupScheduler_shouldIdentifyWhenAssignment2FullyContainsAssignment1() {
        val cleanupScheduler = CleanupScheduler(input)
        val result = cleanupScheduler.checkGroupForFullyContainedAssignments(cleanupScheduler.groups[4])
        assertEquals(true, result)
    }

    @Test
    fun CleanupScheduler_shouldCalculateNumberOfGroupsWithFullyContainedAssignments() {
        val cleanupScheduler = CleanupScheduler(input)
        val result = cleanupScheduler.getNumberOfGroupsWithFullyContainedAssignments()
        assertEquals(2, result)
    }

    @Test
    fun CleanupScheduler_shouldIdentifyWhenAssignmentsOverlap() {
        val cleanupScheduler = CleanupScheduler(input)

        val notOverlappingResult = cleanupScheduler.checkOverlappingAssignments(cleanupScheduler.groups[1])
        val overlappingResult = cleanupScheduler.checkOverlappingAssignments(cleanupScheduler.groups[2])

        assertEquals(false, notOverlappingResult)
        assertEquals(true, overlappingResult)
    }

    @Test
    fun CleanupScheduler_shouldCalculateTotalNumberOfOverlappingAssignments() {
        val cleanupScheduler = CleanupScheduler(input)
        val result = cleanupScheduler.getNumberOfOverlappingAssignments()
        assertEquals(4, result)
    }

}