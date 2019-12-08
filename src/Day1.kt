import java.io.File

fun main() {
    val file = "day1.txt"

    // Part 1
    println(File(file).useLines { it.toList() }.map { it.toInt() }.sumBy { it / 3 - 2 })

    // Part 2
    val fuel = File(file).useLines { it.toList() }.map { it.toInt() }.map {
        var fuel = it / 3 - 2

        var nextFuel = fuel // This is the next one to calculate

        while (nextFuel > 0) {
            nextFuel = nextFuel / 3 - 2
            if (nextFuel > 0) {
                fuel += nextFuel
            }
        }

        fuel    // Put it as the last line so that it gets mapped
    }.sum()

    println(fuel)
}

