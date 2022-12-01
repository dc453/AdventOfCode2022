import java.io.File

fun main() {

    val input = File("src/main/inputs/Day1.txt")
        .readText()
    val highestCalorieAmount = calculateTotalCaloriesForAll(input).max()
    println("Day 1, part 1: $highestCalorieAmount")

}

fun calculateTotalCalories(caloriesList: List<Int>): Int {
    return caloriesList.sum()
}

fun calculateTotalCaloriesForAll(input: String): List<Int> {
    return input.split("\n\n")
        .map {
            val caloriesList = it.split("\n")
                .map { calories -> calories.toInt()}
            calculateTotalCalories(caloriesList)
        }
}