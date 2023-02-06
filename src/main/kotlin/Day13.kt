fun main() {

}

class DistressSignalDecoder(private val signalData: String) {

    private val matchList = Regex("(?<=\\[)(.*)(?=\\])")
    private val matchOuterList = Regex("(?<!\\[([0-9]))(\\,)(?!([0-9]+)\\])")

    fun decode(): List<List<List<Any>?>> {
        return signalData.split("\n\n")
            .map { packetPair ->
                packetPair.split("\n")
                    .map { packet ->
                        println("decoding packet $packet")
                        decodePacketData(packet)
                    }
            }
    }

    fun getPacketPairsInOrder(): List<Int> {
        val packetPairs = mutableListOf<Int>()
        run decode@ {
            decode()
                .forEachIndexed { pairIndex, pair ->
                    pair[0]?.forEachIndexed checkPairOrder@{ packetIndex, _ ->
                        var left = pair[0]?.getOrElse(packetIndex) {
                            packetPairs.add(pairIndex + 1)
                            return@checkPairOrder
                        }
                        var right = pair[1]?.getOrElse(packetIndex) {
                            return@checkPairOrder
                        }
//                        println("comparing left $left and right $right")

                        if (left is Int && right is List<*>) {
                            left = listOf(left)
                        }
                        else if (left is List<*> && right is Int) {
                            right = listOf(right)
                        }

                        if (left is List<*> && right is List<*>) {
                            val listSize = listOf(left.size, right.size).max()
                            for (i in 0 until listSize) {
                                var innerLeft = left.getOrElse(i) {
                                    packetPairs.add(pairIndex + 1)
                                    return@checkPairOrder
                                }
                                var innerRight = right.getOrElse(i) {
                                    return@checkPairOrder
                                }

                                innerLeft = innerLeft as Int
                                innerRight = innerRight as Int
                                if (innerLeft < innerRight) {
                                    packetPairs.add(pairIndex + 1)
                                    return@checkPairOrder
                                } else if (innerLeft > innerRight) {
                                    return@checkPairOrder
                                }
                            }
                            return@checkPairOrder
                        }

                        left = left as Int
                        right = right as Int
                        if (left < right) {
                            packetPairs.add(pairIndex + 1)
                            return@checkPairOrder
                        } else if (left > right) {
                            return@checkPairOrder
                        }
                    }
                }
        }
        return packetPairs
    }

    private fun decodePacketData(packetData: String): List<Any>? {
        println("decoding packet data $packetData")
        return matchList
            .find(packetData)
            ?.value
            ?.split(matchOuterList)
            ?.map {
                (if (!matchList.find(it)?.value.isNullOrEmpty()) {
                    decodePacketData(it)
                }
                else if (it == "[]" || it == "") {
                    emptyList<Any>()
                } else {
                    it.toInt()
                }) as Any
            }
    }

}