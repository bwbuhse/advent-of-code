import java.io.File

fun evaluateIntCode(file: String, noun: Int? = null, verb: Int? = null): Int {
    // IntCode
    val intCode = File(file).useLines { it.toList() }.flatMap { it.split(",") }.map { it.toInt() }.toMutableList()

    // Set noun and verb
    intCode[1] = noun ?: intCode[1]
    intCode[2] = verb ?: intCode[2]

    // Boolean that is set to true once the 99 opcode is reached
    var i = 0

    // Loop over the IntCode
    while (i < intCode.size) {
        val opcode = intCode[i] % 100

        val parameterModes = (intCode[i] / 100).toString().split("").reversed()

        when (opcode) {
            1 -> {
                intCode[intCode[i + 3]] = intCode[intCode[i + 1]] + intCode[intCode[i + 2]]
                i += 4
            }
            2 -> {
                intCode[intCode[i + 3]] = intCode[intCode[i + 1]] * intCode[intCode[i + 2]]
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
                i = intCode.size
            }
            else -> {
                i = intCode.size
                println("You broke something.")
            }
        }
    }

    return intCode[0]
}