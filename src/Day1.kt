import java.io.File

fun main() {
    val file = "day1.txt"

    println(File(file).useLines { it.toList() }.map { it.toInt() }.sumBy { it / 3 - 2 })
}

