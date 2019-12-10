import java.io.File
import kotlin.math.pow

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
        intCode[i] /= 100

        // Puts the parameter modes into a list
        val parameterModes = (0..3).map { it -> (intCode[i] / 10.0.pow(it) % 10).toInt() }

        when (opcode) {
            1 -> {
                if (parameterModes[2] == 1) {
                    intCode[i + 3] = if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } + if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                } else {
                    intCode[intCode[i + 3]] = if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } + if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                }
                i += 4
            }
            2 -> {
                if (parameterModes[2] == 1) {
                    intCode[i + 3] = if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } * if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                } else {
                    intCode[intCode[i + 3]] = if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } * if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                }
                i += 4
            }
            3 -> {
                if (parameterModes[0] == 1) {
                    intCode[i + 1] = readLine()!!.toInt()
                } else {
                    intCode[intCode[i + 1]] = readLine()!!.toInt()
                }
                i += 2
            }
            4 -> {
                println(
                    if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    }
                )
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