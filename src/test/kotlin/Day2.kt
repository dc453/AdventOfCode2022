import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2_Tests {

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

}