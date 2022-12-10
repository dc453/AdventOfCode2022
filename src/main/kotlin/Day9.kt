fun main() {

}

class RopeBridge() {

    val head = RopeKnot()
    val tail = RopeKnot()

    fun move(instructions: String) {
        instructions.split("\n")
            .forEach { instruction ->
                moveStep(instruction)
            }
    }

    private fun moveStep(instruction: String) {
    // TODO update to take individual steps to new point
        val (direction, _steps) = instruction.split(" ")
        val steps = _steps.toInt()
        when (direction) {
            "U" -> {
                head.y += steps
                if (head.y - tail.y > 1) {
                    tail.y += steps - 1
                }
            }
            "R" -> {
                head.x += steps
                if (head.x - tail.x > 1) {
                    tail.x += steps - 1
                }
            }
            "D" -> {
                head.y -= steps
                if (tail.y - head.y > 1) {
                    tail.y -= steps - 2
                }
            }
            "L" -> {
                head.x -= steps
                if (tail.x - head.x > 1) {
                    tail.x -= steps - 2
                }
            }
        }
        if ((head.x - tail.x == 1 && head.y - tail.y == 2) ||
            (head.y - tail.y == 1 && head.x - tail.x == 2)) {
            tail.x += 1
            tail.y += 1
        }
        else if ((tail.x - head.x == 1 && tail.y - head.y == 2) ||
            (tail.y - head.y == 1 && tail.x - head.x == 2)) {
            tail.x -= 1
            tail.y -= 1
        }
    }

}

data class Point(var x: Int = 0, var y: Int = 0)

class RopeKnot(private var _x: Int = 0, private var _y: Int = 0) {

    val history = mutableSetOf<Point>()

    var x: Int
        get() = _x
        set(value) {
            _x = value
            addCurrentPositionToHistory()
        }

    var y: Int
        get() = _y
        set(value) {
            _y = value
            addCurrentPositionToHistory()
        }

    init {
        addCurrentPositionToHistory()
    }

    private fun addCurrentPositionToHistory() {
        history.add(Point(x, y))
    }

    fun step(x: Int, y: Int) {
    }
}