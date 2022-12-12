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
        val result = game.monkeys[2].getMonkeyToThrowTo(game.monkeys[2].items[0])
        assertEquals(1, result)
    }

    @Test
    fun Monkeys_shouldDetermineWhichMonkeyToThrowTo_whenTestIsFalse() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[0].getMonkeyToThrowTo(game.monkeys[0].items[0])
        assertEquals(3, result)
    }

    @Test
    fun Monkeys_whenTakingTurn_shouldDetermineWhereItemsWereThrown() {
        val game = KeepAwayGame(fullInput)
        val result = game.monkeys[0].takeTurn()
        assertEquals(listOf(ItemThrow(500, 3), ItemThrow(620, 3)), result)
    }

    @Test
    fun Monkeys_whenTakingTurn_shouldClearItemsAfterThrow() {
        val game = KeepAwayGame(fullInput)
        game.monkeys[0].takeTurn()
        assertEquals(true, game.monkeys[0].items.isEmpty())
    }

    @Test
    fun Monkeys_whenTakingTurn_shouldCountNumberOfItemsInspected() {
        val game = KeepAwayGame(fullInput)
        game.monkeys[0].takeTurn()
        assertEquals(2, game.monkeys[0].numItemsInspected)
    }

    @Test
    fun KeepAwayGame_whenPlayingRound_shouldThrowItems() {
        val game = KeepAwayGame(fullInput)

        game.playRound()

        assertEquals(listOf(20, 23, 27, 26), game.monkeys[0].items)
        assertEquals(listOf(2080, 25, 167, 207, 401, 1046), game.monkeys[1].items)
        assertEquals(true, game.monkeys[2].items.isEmpty())
        assertEquals(true, game.monkeys[3].items.isEmpty())
    }

    @Test
    fun KeepAwayGame_whenPlayingRounds_shouldKeepCountingInspectedItems() {
        val game = KeepAwayGame(fullInput)

        for (i in 1..20) {
            game.playRound()
        }

        assertEquals(101, game.monkeys[0].numItemsInspected)
        assertEquals(95, game.monkeys[1].numItemsInspected)
        assertEquals(7, game.monkeys[2].numItemsInspected)
        assertEquals(105, game.monkeys[3].numItemsInspected)
    }

    @Test
    fun KeepAwayGame_whenPlayingRounds_shouldCalculateLevelOfMonkeyBusiness() {
        val game = KeepAwayGame(fullInput)

        for (i in 1..20) {
            game.playRound()
        }

        assertEquals(10605, game.monkeyBusinessLevel)
    }

    @Test
    fun KeepAwayGame_whenExtremeWorryModeActive_shouldNotReduceWorryLevels() {
        val game = KeepAwayGame(fullInput, true)
        val result = game.monkeys[0].getNewWorryLevel(game.monkeys[0].items[0])
        assertEquals(1501, result)
    }

    @Test
    fun KeepAwayGame_whenPlayingRounds_andExtremeWorryMode_shouldKeepCountingInspectedItems() {
        val game = KeepAwayGame(fullInput, true)

        for (i in 1..10000) {
            game.playRound()
        }

        assertEquals(52166, game.monkeys[0].numItemsInspected)
        assertEquals(47830, game.monkeys[1].numItemsInspected)
        assertEquals(1938, game.monkeys[2].numItemsInspected)
        assertEquals(52013, game.monkeys[3].numItemsInspected)
    }

    @Test
    fun KeepAwayGame_whenPlayingRounds_andExtremeWorryMode_shouldCalculateLevelOfMonkeyBusiness() {
        val game = KeepAwayGame(fullInput, true)

        for (i in 1..10000) {
            game.playRound()
        }

        assertEquals(2713310158, game.monkeyBusinessLevel)
    }

}