import java.io.File

fun evaluateIntCode(file: String, noun: Int = -1, verb: Int = -1): Int {
    // IntCode
    val intCode = File(file).useLines { it.toList() }.flatMap { it.split(",") }.map { it.toInt() }.toMutableList()

    // Set noun and verb
    intCode[1] = if (noun != -1) noun else intCode[1]
    intCode[2] = if (verb != -1) verb else intCode[2]

    // Boolean that is set to true once the 99 opcode is reached
    var stopped = false
    var i = 0

    // Loop over the IntCode
    while (i < intCode.size) {
        when (intCode[i]) {
            1 -> {
                intCode[intCode[i + 3]] = intCode[intCode[i + 1]] + intCode[intCode[i + 2]];
                i += 4
            }
            2 -> {
                intCode[intCode[i + 3]] = intCode[intCode[i + 1]] * intCode[intCode[i + 2]];
                i += 4
            }
            3 -> {
                intCode[intCode[i + 1]] = readLine()!!.toInt()
                i += 2
            }
            4 -> {
                println(intCode[intCode[i + 1]])
                i += 2
            }
            99 -> {
                stopped = true;
                i = intCode.size
            }
            else -> if (!stopped) {
                stopped = true
                i = intCode.size
                println("You broke something.")
            }
        }
    }

    return intCode[0]
}