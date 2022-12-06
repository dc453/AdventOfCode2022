import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6_Tests {

    @Test
    fun CommunicationSystem_shouldDetermineStartOfNewPackets() {
        val communicationSystem = CommunicationSystem()

        val result1 = communicationSystem.getStartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
        assertEquals(7, result1)

        val result2 = communicationSystem.getStartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertEquals(5, result2)

        val result3 = communicationSystem.getStartOfPacket("nppdvjthqldpwncqszvftbrmjlhg")
        assertEquals(6, result3)
    }

    @Test
    fun CommunicationSystem_shouldDetermineStartOfNewMessages() {
        val communicationSystem = CommunicationSystem()

        val result1 = communicationSystem.getStartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb", true)
        assertEquals(19, result1)

        val result2 = communicationSystem.getStartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz", true)
        assertEquals(23, result2)

        val result3 = communicationSystem.getStartOfPacket("nppdvjthqldpwncqszvftbrmjlhg", true)
        assertEquals(23, result3)
    }

}