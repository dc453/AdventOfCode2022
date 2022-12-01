import java.io.File

fun main() {

    val input = File("src/main/inputs/Day1.txt")
        .readText()
    val caloriesByElf = calculateTotalCaloriesForAll(input)

    val highestCalorieAmount = caloriesByElf.max()
    println("Day 1, part 1: $highestCalorieAmount")

    val totalCaloriesOfTop3 = calculateTopCalories(caloriesByElf)
    println("Day 1, part 2: $totalCaloriesOfTop3")
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

fun calculateTopCalories(caloriesList: List<Int>): Int {
    return caloriesList.sortedDescending()
        .slice(0..2)
        .sum()
}