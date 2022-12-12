import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02_Tests {

    @Test
    fun playingRound_shouldGive1Point_whenChoosingRock() {
        val score = playRound(RPS.ROCK, RPS.PAPER)
        assertEquals(1, score)
    }

    @Test
    fun playingRound_shouldGive2Points_whenChoosingPaper() {
        val score = playRound(RPS.PAPER, RPS.SCISSORS)
        assertEquals(2, score)
    }

    @Test
    fun playingRound_shouldGive3Points_whenChoosingScissors() {
        val score = playRound(RPS.SCISSORS, RPS.ROCK)
        assertEquals(3, score)
    }

    @Test
    fun playingRound_shouldGive6AdditionalPoints_withRockWin() {
        val score = playRound(RPS.ROCK, RPS.SCISSORS)
        assertEquals(7, score)
    }

    @Test
    fun playingRound_shouldGive3AdditionalPoints_withRockTie() {
        val score = playRound(RPS.ROCK, RPS.ROCK)
        assertEquals(4, score)
    }

    @Test
    fun playingRound_shouldGive0AdditionalPoints_withRockLoss() {
        val score = playRound(RPS.ROCK, RPS.PAPER)
        assertEquals(1, score)
    }

    @Test
    fun playingRound_shouldGive6AdditionalPoints_withPaperWin() {
        val score = playRound(RPS.PAPER, RPS.ROCK)
        assertEquals(8, score)
    }

    @Test
    fun playingRound_shouldGive3AdditionalPoints_withPaperTie() {
        val score = playRound(RPS.PAPER, RPS.PAPER)
        assertEquals(5, score)
    }

    @Test
    fun playingRound_shouldGive0AdditionalPoints_withPaperLoss() {
        val score = playRound(RPS.PAPER, RPS.SCISSORS)
        assertEquals(2, score)
    }

    @Test
    fun playingRound_shouldGive6AdditionalPoints_withScissorsWin() {
        val score = playRound(RPS.SCISSORS, RPS.PAPER)
        assertEquals(9, score)
    }

    @Test
    fun playingRound_shouldGive3AdditionalPoints_withScissorsTie() {
        val score = playRound(RPS.SCISSORS, RPS.SCISSORS)
        assertEquals(6, score)
    }

    @Test
    fun playingRound_shouldGive0AdditionalPoints_withScissorsLoss() {
        val score = playRound(RPS.SCISSORS, RPS.ROCK)
        assertEquals(3, score)
    }

    @Test
    fun shouldPlayAllRoundsAndCalculateTotalScoreFromInput() {
        val input = "A Y\n" +
                "B X\n" +
                "C Z"
        val totalScore = playAllRounds(input)
        assertEquals(15, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldLose_whenX_andPlayer2ChoosesRock() {
        val input = "A X"
        val totalScore = playAllRounds(input, true)
        assertEquals(3, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldLose_whenX_andPlayer2ChoosesPaper() {
        val input = "B X"
        val totalScore = playAllRounds(input, true)
        assertEquals(1, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldLose_whenX_andPlayer2ChoosesScissors() {
        val input = "C X"
        val totalScore = playAllRounds(input, true)
        assertEquals(2, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldTie_whenY_andPlayer2ChoosesRock() {
        val input = "A Y"
        val totalScore = playAllRounds(input, true)
        assertEquals(4, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldTie_whenY_andPlayer2ChoosesPaper() {
        val input = "B Y"
        val totalScore = playAllRounds(input, true)
        assertEquals(5, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldTie_whenY_andPlayer2ChoosesScissors() {
        val input = "C Y"
        val totalScore = playAllRounds(input, true)
        assertEquals(6, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldWin_whenZ_andPlayer2ChoosesRock() {
        val input = "A Z"
        val totalScore = playAllRounds(input, true)
        assertEquals(8, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldWin_whenZ_andPlayer2ChoosesPaper() {
        val input = "B Z"
        val totalScore = playAllRounds(input, true)
        assertEquals(9, totalScore)
    }

    @Test
    fun whenDecryptingOutcomes_shouldWin_whenZ_andPlayer2ChoosesScissors() {
        val input = "C Z"
        val totalScore = playAllRounds(input, true)
        assertEquals(7, totalScore)
    }

}