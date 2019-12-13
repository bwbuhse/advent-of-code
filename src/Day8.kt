import java.io.File

fun main() {
    val width = 25
    val height = 6
    val layers = File("day8.txt").useLines { it.toList() }.flatMap { it.chunked(width * height) }

    // Part 1
    val layerWithFewestZeroes = layers.minBy { word -> word.count { it == '0' } }

    if (layerWithFewestZeroes != null) {
        println(layerWithFewestZeroes.count { it == '1' } * layerWithFewestZeroes.count { it == '2' })
    }
}