import java.io.File

fun main() {
    val intCode = File("day6.txt").useLines { it.toList() }.flatMap { it.split(",") }.map { it.toInt() }.toMutableList()
}