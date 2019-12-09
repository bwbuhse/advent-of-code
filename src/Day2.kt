fun main() {
    val output = 19690720

    for (i in 0..99) {
        for (j in 0..99) {
            if (evaluateIntCode("day2.txt", i, j) == output) {
                println(100 * i + j)
            }
        }
    }
}
