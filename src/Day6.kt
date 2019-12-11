import java.io.File
import java.util.*

fun main() {
    // Map of all bodies to a set of bodies that directly orbit them
    val directOrbits = File("day6.txt").useLines { it.toList() }
        .map { it.split(")") }
        .map { listOf(it[0], it[1]) }
        .fold(emptyMap<String, Set<String>>(), { map, it ->
            map + mapOf(it[0] to (map[it[0]]?.union(setOf(it[1])) ?: setOf(it[1])))
        })

    println(sumAllOrbits(directOrbits))
}

// Function used for part 1
fun sumAllOrbits(directOrbits: Map<String, Set<String>>): Int {
    val orbitsToCheck = LinkedList<String>()
    orbitsToCheck += "COM"

    val allOrbits = mapOf("COM" to 0).toMutableMap()

    while (orbitsToCheck.isNotEmpty()) {
        val current = orbitsToCheck.poll()

        directOrbits.getOrDefault(current, emptySet()).forEach {
            orbitsToCheck += it
            allOrbits[it] = allOrbits.getOrDefault(current, 0) + 1
        }
    }

    return allOrbits.values.sum()
}