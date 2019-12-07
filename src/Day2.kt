import java.io.File

fun main() {
    val file = "day2_p1.txt"

    // IntCode
    val intCode = File(file).useLines { it.toList() }.flatMap { it.split(",") }.map { it.toInt() }.toMutableList()

    // Boolean that is set to true once the 99 opcode is reached
    var stopped = false

    // Loop over the IntCode
    for (i in 0 until intCode.size step 4) {
        when (intCode[i]) {
            1 -> if (!stopped) intCode[intCode[i + 3]] = intCode[intCode[i + 1]] + intCode[intCode[i + 2]]
            2 -> if (!stopped) intCode[intCode[i + 3]] = intCode[intCode[i + 1]] * intCode[intCode[i + 2]]
            99 -> stopped = true
        }
    }

    println(intCode[0])
}