import java.io.File
import kotlin.math.abs

fun main() {
    // Get the two lists of points for each line
    val file = File("day3.txt").useLines { it.toList() }.map { it.toPoints() }

    // Find the nearest intersecting point's distance (Part 1)
    val nearestPointDistance = file[0].intersect(file[1]).map { abs(it[0]) + abs(it[1]) }.min()
    println(nearestPointDistance)

    // Find the intersecting point requiring the fewest steps (Part 2)
    val quickestPointDistance = file[0].intersect(file[1]).map { file[0].indexOf(it) + file[1].indexOf(it) + 2 }.min()
    println(quickestPointDistance) // 48038 < answer < 55535 AND answer != 48040
}

// Converts a string of wire directions to a list of points that the wire covers
fun String.toPoints(): List<List<Int>> {
    // Stuff needed for the function
    var curX = 0
    var curY = 0
    val instructions = this.split(",")

    // Other thing
    val points = emptyList<List<Int>>().toMutableSet()

    instructions.forEach {
        for (i in 0 until it.substring(1).toInt()) {
            when (it[0]) {
                'R' -> points += listOf(++curX, curY)
                'L' -> points += listOf(--curX, curY)
                'U' -> points += listOf(curX, ++curY)
                'D' -> points += listOf(curX, --curY)
            }
        }
    }

    // Return set of all the points in the wire
    return points.toList()
}
