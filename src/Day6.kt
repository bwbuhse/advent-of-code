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

    // Part 1
    println(sumAllOrbits(directOrbits))

    // Part 2
    println(minTransfersBtwnBodies(directOrbits))
}

// Function used for part 1
private fun sumAllOrbits(directOrbits: Map<String, Set<String>>): Int {
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

// Functions used for part 2
private fun minTransfersBtwnBodies(
    directOrbits: Map<String, Set<String>>,
    body1: String = "SAN",
    body2: String = "YOU"
): Int {
    val path1 = createPath(directOrbits, destBody = body1)
    val path2 = createPath(directOrbits, destBody = body2)

    val intersectionSize = path1.intersect(path2).size

    return (path1.size - intersectionSize) + (path2.size - intersectionSize)
}

private fun createPath(
    directOrbits: Map<String, Set<String>>,
    currentBody: String = "COM",
    destBody: String = "COM",
    path: List<String> = emptyList()
): List<String> {
    val orbitsFromCurrentBody = directOrbits.getOrDefault(currentBody, emptySet())
    val currentPath = path + currentBody

    return when {
        orbitsFromCurrentBody.isEmpty() -> emptyList()
        orbitsFromCurrentBody.contains(destBody) -> currentPath
        else -> orbitsFromCurrentBody.flatMap { createPath(directOrbits, it, destBody, currentPath) }
    }
}