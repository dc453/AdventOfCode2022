import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8_Tests {

    @Test
    fun TreeHouseSite_shouldCountEdgesAsVisibleTrees() {
        val input = "9999\n" +
                "9119\n" +
                "9119\n" +
                "9999"
        val treeHouseSite = TreeHouseSite(input)
        assertEquals(12, treeHouseSite.getNumVisibleTrees())
    }

    @Test
    fun TreeHouseSite_shouldCountInteriorTreesAsVisibleTrees_whenTallerThanEdges() {
        val input = "30373\n" +
                "25512\n" +
                "65332\n" +
                "33549\n" +
                "35390"
//        val input = "99999\n" +
//                "91519\n" +
//                "95159\n" +
//                "91519\n" +
//                "99199"
        val treeHouseSite = TreeHouseSite(input)

        val result = treeHouseSite.getNumVisibleTrees()

        // 16 edge, 5 interior
        assertEquals(21, result)
    }
}