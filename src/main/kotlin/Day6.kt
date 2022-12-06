import java.io.File

fun main() {

    val input = File("src/main/inputs/Day6.txt")
        .readText()
    val communicationSystem = CommunicationSystem()
    println("Day 6, part 1: ${communicationSystem.getStartOfPacket(input)}")
    println("Day 6, part 2: ${communicationSystem.getStartOfPacket(input, true)}")

}

class CommunicationSystem {

    fun getStartOfPacket(datastream: String, enableFullMessageSearch: Boolean = false): Int {
        val datastreamCharacters = datastream.split("")
            .filter { it.isNotEmpty() }
        datastreamCharacters.forEachIndexed { index, character ->
            if (enableFullMessageSearch) {
                var characterSet = mutableSetOf<String>(character)
                for (i in 1..13) {
                    characterSet.add(datastreamCharacters[index + i])
                }
                if (characterSet.size == 14) {
                    return index + 14
                }
            } else {
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
        }
        return -1
    }

}