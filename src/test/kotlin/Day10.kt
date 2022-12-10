import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day10_Tests {

    @Test
    fun VideoSystem_whenNoOpInstruction_shouldAdvanceCycle() {
        val vs = VideoSystem()
        vs.draw("noop")
        assertEquals(1, vs.cycle)
    }

    @Test
    fun VideoSystem_shouldRunMultipleInstructions() {
        val input = "noop\n" +
                "noop"
        val vs = VideoSystem()

        vs.draw(input)

        assertEquals(2, vs.cycle)
    }

    @Test
    fun VideoSystem_whenAddxCommand_shouldAdvanceCycleBy2() {
        val input = "addX 3"
        val vs = VideoSystem()

        vs.draw(input)

        assertEquals(2, vs.cycle)
    }

    @Test
    fun VideoSystem_whenAddxCommand_shouldAddValue() {
        val input = "addX 3"
        val vs = VideoSystem()

        assertEquals(1, vs.x)

        vs.draw(input)
        assertEquals(4, vs.x)
    }

    @Test
    fun VideoSystem_shouldCalculateSignalStrengthEvery20Cycles() {
        val input = File("src/test/inputs/Day10.txt")
            .readText()
        val vs = VideoSystem()

        vs.draw(input)

        val result: SignalStrength = vs.signalStrengths[0]
        assertEquals(420, result.strength)
    }

    @Test
    fun VideoSystem_shouldGetSumOfSpecifiedSignalStrengths() {
        val input = File("src/test/inputs/Day10.txt")
            .readText()
        val vs = VideoSystem()

        vs.draw(input)

        val result = vs.getSignalStrengthTotal(listOf(20, 60, 100, 140, 180, 220))
        assertEquals(13140, result)
    }

    @Test
    fun VideoSystem_shouldOutputDisplay() {
        val input = File("src/test/inputs/Day10.txt")
            .readText()
        val vs = VideoSystem()

        vs.draw(input)

        val expected = "##..##..##..##..##..##..##..##..##..##..\n" +
                       "###...###...###...###...###...###...###.\n" +
                       "####....####....####....####....####....\n" +
                       "#####.....#####.....#####.....#####.....\n" +
                       "######......######......######......####\n" +
                       "#######.......#######.......#######....."
        assertEquals(expected, vs.getDisplay())
    }

}