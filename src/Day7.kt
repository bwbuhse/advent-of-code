import kotlin.math.max

fun main() {
    val file = "day7.txt"

    var maxOut = Integer.MIN_VALUE

    for (a in 0..4) {
        for (b in 0..4) {
            for (c in 0..4) {
                for (d in 0..4) {
                    for (e in 0..4) {
                        if (setOf(a, b, c, d, e).size == 5) {

                            val ioList = mutableListOf(a, 0)
                            evaluateIntCode(file, outputToList = true, ioList = ioList)
                            ioList.add(0, b)
                            evaluateIntCode(file, outputToList = true, ioList = ioList)
                            ioList.add(0, c)
                            evaluateIntCode(file, outputToList = true, ioList = ioList)
                            ioList.add(0, d)
                            evaluateIntCode(file, outputToList = true, ioList = ioList)
                            ioList.add(0, e)
                            evaluateIntCode(file, outputToList = true, ioList = ioList)

                            maxOut = max(maxOut, ioList[0])
                        }
                    }
                }
            }
        }
    }

    println("\n\nMAX OUTPUT: $maxOut")
}