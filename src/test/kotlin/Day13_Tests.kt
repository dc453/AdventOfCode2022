import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class Day13_Tests {

    @Test
    fun DistressSignalDecoder_shouldParseSignalPairs() {
        val input = "[1,1,3,1,1]\n" +
                    "[1,1,5,1,1]"
        val result = DistressSignalDecoder(input).decode()
        assertAll("decoded signal pair",
            { assertEquals(listOf(1,1,3,1,1), result[0][0]) },
            { assertEquals(listOf(1,1,5,1,1), result[0][1]) }
        )

    }

    @Test
    fun DistressSignalDecoder_shouldParseMultipleSignalPairs() {
        val input = "[1,1,3,1,1]\n" +
                    "[1,1,5,1,1]\n" +
                    "\n" +
                    "[1,2,3,4,5]\n" +
                    "[5,4,3,2,1]"
        val result = DistressSignalDecoder(input).decode()
        assertAll("decoded signal pairs",
            { assertEquals(listOf(1,2,3,4,5), result[1][0]) },
            { assertEquals(listOf(5,4,3,2,1), result[1][1]) }
        )
    }

    @Test
    fun DistressSignalDecoder_shouldParseNestedListsInSignalPairs() {
        val input = "[[1],[2,3,4]]\n" +
                    "[[1],4]"
        val result = DistressSignalDecoder(input).decode()
        assertAll("decoded nested lists",
            { assertEquals(listOf(listOf(1), listOf(2,3,4)), result[0][0]) },
            { assertEquals(listOf(listOf(1), 4), result[0][1]) }
        )
    }

    @Test
    fun DistressSignalDecoder_shouldParseEmptyListsInSignalPairs() {
        val input = "[[[]]]\n" +
                    "[[]]"
        val result = DistressSignalDecoder(input).decode()
        println(result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingInts() {
        val input = "[1,1,3,1,1]\n" +
                    "[1,1,5,1,1]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(1), result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingLists() {
        val input = "[[1,1,3],1,1]\n" +
                    "[[1,1,5],1,1]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(1), result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingLists_andLeftPacketShorter() {
        val input = "[[1,1],1,1]\n" +
                    "[[1,1,5],1,1]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(1), result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingLists_andLeftPacketLonger() {
        val input = "[[1,1,3],1,1]\n" +
                    "[[1,1],1,1]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(), result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingLists_andPacketsIdentical() {
        val input = "[[1,1],3,1]\n" +
                    "[[1,1],5,1]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(1), result)
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_whenComparingListsAndInts() {
        val inputWhenRightIsList = "[1,1,1]\n" +
                "[[1,1],1,1]"
        val resultWhenRightIsList = DistressSignalDecoder(inputWhenRightIsList).getPacketPairsInOrder()
        val inputWhenLeftIsList = "[[1,1],1,1]\n" +
                "[1,1,1]"
        val resultWhenLeftIsList = DistressSignalDecoder(inputWhenLeftIsList).getPacketPairsInOrder()

        assertAll(
            { assertEquals(listOf(1), resultWhenRightIsList) },
            { assertEquals(listOf(), resultWhenLeftIsList) }
        )
    }

    @Test
    fun DistressSignalDecoder_shouldDeterminePacketsInOrder_fromFullInput() {
        val input = "[1,1,3,1,1]\n" +
                "[1,1,5,1,1]\n" +
                "\n" +
                "[[1],[2,3,4]]\n" +
                "[[1],4]\n" +
                "\n" +
                "[9]\n" +
                "[[8,7,6]]\n" +
                "\n" +
                "[[4,4],4,4]\n" +
                "[[4,4],4,4,4]\n" +
                "\n" +
                "[7,7,7,7]\n" +
                "[7,7,7]\n" +
                "\n" +
                "[]\n" +
                "[3]\n" +
                "\n" +
                "[[[]]]\n" +
                "[[]]\n" +
                "\n" +
                "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
                "[1,[2,[3,[4,[5,6,0]]]],8,9]"
        val result = DistressSignalDecoder(input).getPacketPairsInOrder()
        assertEquals(listOf(1,2,4,6), result)
    }
}