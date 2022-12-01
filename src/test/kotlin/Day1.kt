import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1_Tests {

    val input = "1000\n" +
            "2000\n" +
            "3000\n" +
            "\n" +
            "4000\n" +
            "\n" +
            "5000\n" +
            "6000\n" +
            "\n" +
            "7000\n" +
            "8000\n" +
            "9000\n" +
            "\n" +
            "10000"

    @Test
    fun shouldCalculateTotalCaloriesFromList() {
        val input = listOf(1000, 2000, 3000)
        val totalCalories = calculateTotalCalories(input)
        assertEquals(6000, totalCalories)
    }

    @Test
    fun shouldParseTotalCaloriesForEachElf() {
        val parsedElfCarries = calculateTotalCaloriesForAll(input)
        assertEquals(listOf(6000, 4000,11000, 24000, 10000), parsedElfCarries)
    }

}