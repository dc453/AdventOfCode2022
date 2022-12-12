import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Day09_Tests {

    @Test
    fun RopeBridge_shouldHaveRopeHeadAtStart() {
        val bridge = RopeBridge()
        assertEquals(0, bridge.head.x)
        assertEquals(0, bridge.head.y)
    }

    @Test
    fun RopeBridge_shouldMoveHeadUp() {
        val bridge = RopeBridge()

        bridge.move("U 4")

        assertEquals(0, bridge.head.x)
        assertEquals(4, bridge.head.y)
    }

    @Test
    fun RopeBridge_shouldMoveHeadRight() {
        val bridge = RopeBridge()

        bridge.move("R 4")

        assertEquals(4, bridge.head.x)
        assertEquals(0, bridge.head.y)
    }

    @Test
    fun RopeBridge_shouldMoveHeadDown() {
        val bridge = RopeBridge()

        bridge.move("U 4\n" +
                "D 2")

        assertEquals(0, bridge.head.x)
        assertEquals(2, bridge.head.y)
    }

    @Test
    fun RopeBridge_shouldMoveHeadLeft() {
        val bridge = RopeBridge()

        bridge.move("R 4\n" +
                "L 2")

        assertEquals(2, bridge.head.x)
        assertEquals(0, bridge.head.y)
    }

    @Test
    fun RopeBridge_shouldHaveTailHeadAtStart() {
        val bridge = RopeBridge()
        assertEquals(0, bridge.tail.x)
        assertEquals(0, bridge.tail.y)
    }

    @Test
    fun RopeBridge_shouldMoveTailUp() {
        val bridge = RopeBridge()

        bridge.move("U 4")

        assertEquals(0, bridge.tail.x)
        assertEquals(3, bridge.tail.y)
    }

    @Test
    fun RopeBridge_shouldMoveTailRight() {
        val bridge = RopeBridge()

        bridge.move("R 4")

        assertEquals(3, bridge.tail.x)
        assertEquals(0, bridge.tail.y)
    }

    @Test
    fun RopeBridge_shouldMoveTailDown() {
        val bridge = RopeBridge()

        bridge.move("U 4\n" +
                "D 3")

        assertEquals(0, bridge.tail.x)
        assertEquals(2, bridge.tail.y)
    }

    @Test
    fun RopeBridge_shouldMoveTailLeft() {
        val bridge = RopeBridge()

        bridge.move("R 4\n" +
                "L 3")

        assertEquals(2, bridge.tail.x)
        assertEquals(0, bridge.tail.y)
    }

//    @Test
//    fun RopeBridge_shouldMoveTailUpperRight() {
//        val bridge = RopeBridge()
//
//        bridge.move("R 2\n" +
//                "U 1")
//        assertEquals(1, bridge.tail.x)
//        assertEquals(0, bridge.tail.y)
//
//        bridge.move("U 1")
//        println("head ${bridge.head.x},${bridge.head.y}\n" +
//                "tail ${bridge.tail.x},${bridge.tail.y}")
//        assertEquals(2, bridge.tail.x)
//        assertEquals(1, bridge.tail.y)
//    }
//
//    @Test
//    fun RopeBridge_shouldMoveTailLowerRight() {
//        val bridge = RopeBridge()
//
//        bridge.move("U 4\n" +
//                "R 3\n" +
//                "D 4")
//        assertEquals(1, bridge.tail.x)
//        assertEquals(0, bridge.tail.y)
//
//        bridge.move("U 1")
//        println("head ${bridge.head.x},${bridge.head.y}\n" +
//                "tail ${bridge.tail.x},${bridge.tail.y}")
//        assertEquals(2, bridge.tail.x)
//        assertEquals(1, bridge.tail.y)
//    }

    @Test
    fun RopeKnot_shouldSaveHistory() {
        val input = "R 4\n" +
                "U 4"
        val bridge = RopeBridge()

        bridge.move(input)

        val expected = mutableSetOf(
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0),
            Point(4, 0),
            Point(4, 1),
            Point(4, 2),
            Point(4, 3),
            Point(4, 4)
        )
        assertContentEquals(expected, bridge.head.history.asIterable())
    }

    @Test
    fun RopeKnot_shouldMoveTailDiagonally() {
        val input ="R 4\n" +
                "U 4\n" +
                "L 3\n" +
                "D 1\n" +
                "R 4\n" +
                "D 1\n" +
                "L 5\n" +
                "R 2"
        val bridge = RopeBridge()

        bridge.move(input)

        println("head ${bridge.head.history.size} | tail ${bridge.tail.history.size}")
//        bridge.tail.history.forEach { println(it) }

        assertEquals(13, bridge.tail.history.size)
    }

}