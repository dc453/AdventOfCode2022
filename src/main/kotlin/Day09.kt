fun main() {

}

class RopeBridge() {

    // TODO: update RopeBridge to control head and knot movements one step at a time

    val head = RopeKnot()
    val tail = RopeKnot()
    var numSteps = 0

    fun move(instructions: String) {
        instructions.split("\n")
            .forEach { instruction ->
                moveStep(instruction)
            }
    }

    private fun moveStep(instruction: String) {
        val (direction, _steps) = instruction.split(" ")
        val steps = _steps.toInt()
        numSteps++
        when (direction) {
            "U" -> {
                head.step(0, steps)
                val movedTail = shiftTailDiagonallyIfNeeded()
                if (!movedTail && head.y - tail.y > 1) {
                    tail.step(0, steps - 1)
                }
            }
            "R" -> {
                head.step(steps, 0)
                val movedTail = shiftTailDiagonallyIfNeeded()
                if (!movedTail && head.x - tail.x > 1) {
                    tail.step(steps - 1, 0)
                }
            }
            "D" -> {
                head.step(0, 0 - steps)
                val movedTail = shiftTailDiagonallyIfNeeded()
                if (!movedTail && tail.y - head.y > 1) {
                    tail.step(0, 0 - steps + 2)
                }
            }
            "L" -> {
                head.step(0 - steps, 0)
                val movedTail = shiftTailDiagonallyIfNeeded()
                if (!movedTail && tail.x - head.x > 1) {
                    tail.step(0 - steps + 2, 0)
                }
            }
        }
        println("step count: ${numSteps}\n" +
                "head: ${head.x},${head.y}\n" +
                "tail: ${tail.x},${tail.y}")
    }

    private fun shiftTailDiagonallyIfNeeded(): Boolean {
        if ((head.x - tail.x == 1 && head.y - tail.y == 2) || (head.y - tail.y == 1 && head.x - tail.x == 2)) {
            tail.step(1, 1)
            return true
        } else if ((tail.x - head.x == 1 && tail.y - head.y == 2) || (tail.y - head.y == 1 && tail.x - head.x == 2)) {
            tail.step(-1, -1)
            return true
        } else if ((head.x - tail.x == 1 && tail.y - head.y == 2) || (tail.y - head.y == 1 && head.x - tail.x == 2)) {
            tail.step(1, -1)
            return true
        } else if ((tail.x - head.x == 1 && head.y - tail.y == 2) || (head.y - tail.y == 1 && tail.x - head.x == 2)) {
            tail.step(-1, 1)
            return true
        }
        return false
    }

}

data class Point(var x: Int = 0, var y: Int = 0)

class RopeKnot(private var _x: Int = 0, private var _y: Int = 0) {

    val history = mutableSetOf<Point>()
    var concurrentSteps = false

    var x: Int
        get() = _x
        set(value) {
            _x = value
            if (!concurrentSteps) addCurrentPositionToHistory()
        }

    var y: Int
        get() = _y
        set(value) {
            _y = value
            if (!concurrentSteps) addCurrentPositionToHistory()
        }

    init {
        addCurrentPositionToHistory()
    }

    private fun addCurrentPositionToHistory() {
        history.add(Point(x, y))
    }

    fun step(stepsX: Int, stepsY: Int) {
        if (stepsX * stepsX == stepsY * stepsY) {
            // handle tail knot diagonal shift
            println("?")
            concurrentSteps = true
        }
        if (stepsX > 0) {
            for (i in 0 until stepsX) {
                x++
                concurrentSteps = false
            }
        } else if (stepsX < 0) {
            for (i in stepsX until 0) {
                x--
                concurrentSteps = false
            }
        }
        if (stepsY > 0) {
            for (i in 0 until stepsY) {
                y++
            }
        } else if (stepsY < 0) {
            for (i in stepsY until 0) {
                y--
            }
        }
    }
}