fun main() {

}

class TreeHouseSite(input: String) {

    val trees = input.split("\n")
        .map { row ->
            row.split("")
                .filter { col -> col != "" }
                .map { col -> col.toInt() }
        }

    fun getNumVisibleTrees(): Int {
        var numVisibleTrees = 0
        val treesY = trees.size
        val treesX = trees[0].size

        trees.forEachIndexed { index, row ->
            numVisibleTrees += if (index == 0 || index == treesY - 1) {
                row.size
            } else {
                2
            }
        }

        for (y in 1 until treesY - 1) {
            for (x in 1 until treesX - 1) {
                val curTreeHeight = trees[y][x]
                println("x $x y $y height $curTreeHeight")
                if (x == 1 && y == 1) {
                    // top left
                    if (trees[y - 1][x] < curTreeHeight || trees[y][x - 1] < curTreeHeight) {
                        numVisibleTrees++
                    }
                } else if (x == 1 && y == treesY - 2) {
                    // bottom left
                    if (trees[y + 1][x] < curTreeHeight || trees[y][x - 1] < curTreeHeight) {
                        numVisibleTrees++
                    }
                } else if (x == treesX - 2 && y == 1) {
                    // top right
                    if (trees[y - 1][x] < curTreeHeight || trees[y][x + 1] < curTreeHeight) {
                        numVisibleTrees++
                    }
                } else if (x == treesX - 2 && y == treesY - 2) {
                    // bottom right
                    if (trees[y + 1][x] < curTreeHeight || trees[y][x + 1] < curTreeHeight) {
                        numVisibleTrees++
                    }
                } else {
                    // TODO: handle non-corners
                }
            }
        }

        return numVisibleTrees
    }

}