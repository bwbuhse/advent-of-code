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

    println("-----")

    // Part 2
    val image = layers.fold((0 until (width * height)).toList().map { '2' },
        { image, layer -> layer.mapIndexed { i, it -> if (image[i] != '2') image[i] else it } })
        .fold("", { string, char -> string + char }).chunked(width).map { it + "\n" }
        .fold("", { string, char -> string + char })
        .map { it -> if (it == '0') ' ' else if (it == '1') 35.toChar() else it }
        .fold("", { string, char -> string + char })


    println(image)
}