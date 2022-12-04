import java.io.File

fun main() {

    val input = File("src/main/inputs/Day4.txt")
        .readText()
    val cleanupScheduler = CleanupScheduler(input)
    println("Day 4, part 1: ${cleanupScheduler.getNumberOfGroupsWithFullyContainedAssignments()}")

}

class CleanupScheduler(input: String) {

    val groups = input.split("\n")
        .map { group ->
            group.split(",")
                .map { range ->
                    val assignment = range.split("-")
                        .map { it.toInt() }
                    (assignment[0] .. assignment[1]).toList()
                }
        }

    fun checkGroupForFullyContainedAssignments(group: List<List<Int>>): Boolean {
        return group[0].containsAll(group[1]) || group[1].containsAll(group[0])
    }

    fun getNumberOfGroupsWithFullyContainedAssignments(): Int {
        return groups.filter {
            checkGroupForFullyContainedAssignments(it)
        }.size
    }

}