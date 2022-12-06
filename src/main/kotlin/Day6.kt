import java.io.File

fun main() {

    val input = File("src/main/inputs/Day6.txt")
        .readText()
    val communicationSystem = CommunicationSystem()
    println("Day 6, part 1: ${communicationSystem.getStartOfPacket(input)}")

}

class CommunicationSystem {

    fun getStartOfPacket(datastream: String): Int {
        val datastreamCharacters = datastream.split("")
            .filter { it.isNotEmpty() }
        datastreamCharacters.forEachIndexed { index, character ->
            val characterSet = setOf(
                character,
                datastreamCharacters[index + 1],
                datastreamCharacters[index + 2],
                datastreamCharacters[index + 3]
            )
            if (characterSet.size == 4) {
                return index + 4
            }
        }
        return -1
    }

}