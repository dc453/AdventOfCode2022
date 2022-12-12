import java.io.File

fun main() {

    val input = File("src/main/inputs/Day06.txt")
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
            val packetSize = if (enableFullMessageSearch) {
                14
            } else {
                4
            }
            val characterSet = mutableSetOf(character)
            for (i in 1 until packetSize) {
                characterSet.add(datastreamCharacters[index + i])
            }
            if (characterSet.size == packetSize) {
                return index + packetSize
            }
        }
        return -1
    }

}