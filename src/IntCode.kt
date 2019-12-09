import java.io.File

fun intCode(file: String, noun: Int, verb: Int): Int {
    // IntCode
    val intCode = File(file).useLines { it.toList() }.flatMap { it.split(",") }.map { it.toInt() }.toMutableList()

    // Set noun and verb
    intCode[1] = noun
    intCode[2] = verb

    // Boolean that is set to true once the 99 opcode is reached
    var stopped = false
    var broken = false

    // Loop over the IntCode
    for (i in 0 until intCode.size step 4) {
        when (intCode[i]) {
            1 -> if (!stopped) intCode[intCode[i + 3]] = intCode[intCode[i + 1]] + intCode[intCode[i + 2]]
            2 -> if (!stopped) intCode[intCode[i + 3]] = intCode[intCode[i + 1]] * intCode[intCode[i + 2]]
            99 -> stopped = true
            else -> if (!stopped) {
                broken = true
            }
        }
    }

    return intCode[0]
}