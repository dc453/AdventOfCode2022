import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5_Tests {

    val input = "    [D]    \n" +
                "[N] [C]    \n" +
                "[Z] [M] [P]\n" +
                " 1   2   3 \n" +
                "\n" +
                "move 1 from 2 to 1\n" +
                "move 3 from 1 to 3\n" +
                "move 2 from 2 to 1\n" +
                "move 1 from 1 to 2"

    @Test
    fun CargoCrane_shouldInterpretStartingStackArrangement() {
        val cargoCrane = CargoCrane(input)
        assertEquals(listOf("Z", "N"), cargoCrane.supplyStacks[0])
        assertEquals(listOf("M", "C", "D"), cargoCrane.supplyStacks[1])
        assertEquals(listOf("P"), cargoCrane.supplyStacks[2])
    }

    @Test
    fun CargoCrane_shouldInterpretMovingInstructions() {
        val cargoCrane = CargoCrane(input)
        assertEquals(listOf(1, 2, 1), cargoCrane.instructions[0])
        assertEquals(listOf(3, 1, 3), cargoCrane.instructions[1])
        assertEquals(listOf(2, 2, 1), cargoCrane.instructions[2])
        assertEquals(listOf(1, 1, 2), cargoCrane.instructions[3])
    }

    @Test
    fun CargoCrane_shouldArrangeStacksAccordingToInstructions() {
        val cargoCrane = CargoCrane(input)

        cargoCrane.arrangeStacks()

        val expected = listOf<List<String>>(
            listOf("C"),
            listOf("M"),
            listOf("P", "D", "N", "Z")
        )
        assertEquals(expected, cargoCrane.supplyStacks)
    }

    @Test
    fun CargoCrane_shouldDetermineTopCrates() {
        val cargoCrane = CargoCrane(input)
        cargoCrane.arrangeStacks()

        val result = cargoCrane.getTopCrates()

        assertEquals("CMZ", result)
    }

    @Test
    fun CargoCrane_whenModel9001_shouldArrangeMultipleCratesAtOnce() {
        val cargoCrane = CargoCrane(input, true)

        cargoCrane.arrangeStacks()

        val expected = listOf<List<String>>(
            listOf("M"),
            listOf("C"),
            listOf("P", "Z", "N", "D")
        )
        assertEquals(expected, cargoCrane.supplyStacks)
    }

}