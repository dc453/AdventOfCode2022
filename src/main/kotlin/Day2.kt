import java.io.File

fun main() {

    val input = File("src/main/inputs/Day2.txt")
        .readText()
    val totalScore = playAllRounds(input)
    println("Day 2, part 1: $totalScore")

}

enum class RPS {
    ROCK, PAPER, SCISSORS
}

private const val WIN_SCORE = 6
private const val TIE_SCORE = 3
private const val LOSE_SCORE = 0

private val ENCRYPTED_COMMANDS = mapOf<String, Enum<RPS>>(
    "A" to RPS.ROCK,
    "B" to RPS.PAPER,
    "C" to RPS.SCISSORS,
    "X" to RPS.ROCK,
    "Y" to RPS.PAPER,
    "Z" to RPS.SCISSORS
)


fun playRound(player1Choice: Enum<RPS>?, player2Choice: Enum<RPS>?): Int {
    var score = LOSE_SCORE
    score += when (player1Choice) {
        RPS.ROCK -> 1
        RPS.PAPER -> 2
        RPS.SCISSORS -> 3
        else -> LOSE_SCORE
    }
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

fun playAllRounds(input: String): Int {
    val parsedInput = input.split("\n")
    var score = 0
    parsedInput.forEach { roundInput ->
        val playerCommands = roundInput.split(" ")
        score += playRound(
            ENCRYPTED_COMMANDS[playerCommands[1]],
            ENCRYPTED_COMMANDS[playerCommands[0]]
        )
    }
    return score
}