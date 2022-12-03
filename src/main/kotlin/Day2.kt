import java.io.File

fun main() {

    val input = File("src/main/inputs/Day2.txt")
        .readText()
    val totalScore = playAllRounds(input)
    println("Day 2, part 1: $totalScore")

    val totalScoreWhenDecryptingOutcomes = playAllRounds(input, true)
    println("Day 2, part 2: $totalScoreWhenDecryptingOutcomes")

}

enum class RPS {
    ROCK, PAPER, SCISSORS
}

private const val WIN_SCORE = 6
private const val TIE_SCORE = 3
private const val LOSE_SCORE = 0

private val commandValues = mapOf<Enum<RPS>, Int>(
    RPS.ROCK to 1,
    RPS.PAPER to 2,
    RPS.SCISSORS to 3
)
private val player2CommandKeys = mapOf<String, Enum<RPS>>(
    "A" to RPS.ROCK,
    "B" to RPS.PAPER,
    "C" to RPS.SCISSORS
)
private val player1CommandKeys = mapOf<String, Enum<RPS>>(
    "X" to RPS.ROCK,
    "Y" to RPS.PAPER,
    "Z" to RPS.SCISSORS
)


fun playRound(player1Choice: Enum<RPS>?, player2Choice: Enum<RPS>?): Int {
    var score = commandValues.getOrDefault(player1Choice, 0)
    score += when (player1Choice) {
        RPS.ROCK -> when (player2Choice) {
            RPS.SCISSORS -> WIN_SCORE
            RPS.ROCK -> TIE_SCORE
            else -> LOSE_SCORE
        }
        RPS.PAPER -> when (player2Choice) {
            RPS.ROCK -> WIN_SCORE
            RPS.PAPER -> TIE_SCORE
            else -> LOSE_SCORE
        }
        RPS.SCISSORS -> when (player2Choice) {
            RPS.PAPER -> WIN_SCORE
            RPS.SCISSORS -> TIE_SCORE
            else -> LOSE_SCORE
        }
        else -> LOSE_SCORE
    }
    return score
}

fun playAllRounds(input: String, enableOutcomeMode: Boolean = false): Int {
    val parsedInput = input.split("\n")
    var score = 0
    parsedInput.forEach { roundInput ->
        val playerCommands = roundInput.split(" ")
        val player2Choice = player2CommandKeys[playerCommands[0]]
        val player1Choice = if (enableOutcomeMode) {
            determinePlayerChoiceByOutcome(playerCommands[1], player2Choice)
        } else {
            player1CommandKeys[playerCommands[1]]
        }
        score += playRound(
            player1Choice,
            player2Choice
        )
    }
    return score
}

private fun determinePlayerChoiceByOutcome(encryptedOutcome: String, player2Choice: Enum<RPS>?) =
    when (encryptedOutcome) {
        "X" -> when (player2Choice) {
            RPS.ROCK -> RPS.SCISSORS
            RPS.PAPER -> RPS.ROCK
            RPS.SCISSORS -> RPS.PAPER
            else -> null
        }
        "Y" -> when (player2Choice) {
            RPS.ROCK -> RPS.ROCK
            RPS.PAPER -> RPS.PAPER
            RPS.SCISSORS -> RPS.SCISSORS
            else -> null
        }
        "Z" -> when (player2Choice) {
            RPS.ROCK -> RPS.PAPER
            RPS.PAPER -> RPS.SCISSORS
            RPS.SCISSORS -> RPS.ROCK
            else -> null
        }
        else -> null
    }