import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11_Tests {

    val fullInput = "Monkey 0:\n" +
            "  Starting items: 79, 98\n" +
            "  Operation: new = old * 19\n" +
            "  Test: divisible by 23\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 1:\n" +
            "  Starting items: 54, 65, 75, 74\n" +
            "  Operation: new = old + 6\n" +
            "  Test: divisible by 19\n" +
            "    If true: throw to monkey 2\n" +
            "    If false: throw to monkey 0\n" +
            "\n" +
            "Monkey 2:\n" +
            "  Starting items: 79, 60, 97\n" +
            "  Operation: new = old * old\n" +
            "  Test: divisible by 13\n" +
            "    If true: throw to monkey 1\n" +
            "    If false: throw to monkey 3\n" +
            "\n" +
            "Monkey 3:\n" +
            "  Starting items: 74\n" +
            "  Operation: new = old + 3\n" +
            "  Test: divisible by 17\n" +
            "    If true: throw to monkey 0\n" +
            "    If false: throw to monkey 1"

    @Test
    fun KeepAwayGame_shouldHaveMonkeys() {
        val game = KeepAwayGame(fullInput)
        assertEquals(4, game.monkeys.size)
    }

    @Test
    fun Monkeys_shouldDetermineStartingItems() {
        val game = KeepAwayGame(fullInput)
        assertEquals(listOf(79, 98), game.monkeys[0].items)
    }

    @Test
    fun Monkeys_shouldDetermineWorryModifier_whenMultiplication() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[0].getNewWorryLevel(79)
        assertEquals(500, result)
    }

    @Test
    fun Monkeys_shouldDetermineWorryModifier_whenAddition() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[1].getNewWorryLevel(54)
        assertEquals(20, result)
    }

    @Test
    fun Monkeys_shouldDetermineWorryModifier_whenUsingOldLevel() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[2].getNewWorryLevel(79)
        assertEquals(2080, result)
    }

    @Test
    fun Monkeys_shouldDetermineWhichMonkeyToThrowTo_whenTestIsTrue() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[2].getMonkeyToThrowTo()
        assertEquals(1, result)
    }

    @Test
    fun Monkeys_shouldDetermineWhichMonkeyToThrowTo_whenTestIsFalse() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[0].getMonkeyToThrowTo()
        assertEquals(3, result)
    }

}