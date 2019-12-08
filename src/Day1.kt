import java.io.File

fun main() {
    val file = "day1.txt"

    // Part 1
    println(File(file).useLines { it.toList() }.map { it.toInt() }.sumBy { it / 3 - 2 })

    // Part 2
    val fuel = File(file).useLines { it.toList() }.map { it.toInt() }.map { it / 3 - 2 }.toMutableList()

    for (i in 0 until fuel.size) {
        var nextFuel = fuel[i] // This is the next one to calculate

        while (nextFuel > 0) {
            nextFuel = nextFuel / 3 - 2
            if (nextFuel > 0) {
                fuel[i] += nextFuel
            }
        }
    }

    println(fuel.sum()) // Should be less than 4944360
}

