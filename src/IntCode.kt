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
        val code = intCode[i] / 100

        // Puts the parameter modes into a list
        val parameterModes = (0..3).map { (code / 10.0.pow(it) % 10).toInt() }

        when (opcode) {
            // Add
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
            // Multiply
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
            // Input
            3 -> {
                if (parameterModes[0] == 1) {
                    intCode[i + 1] = readLine()!!.toInt()
                } else {
                    intCode[intCode[i + 1]] = readLine()!!.toInt()
                }
                i += 2
            }
            // Output
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
            // Jump if true
            5 -> {
                if (if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } != 0
                ) {
                    i = if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                } else {
                    i += 3
                }
            }
            // Jump if false
            6 -> {
                if (if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } == 0
                ) {
                    i = if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                } else {
                    i += 3
                }
            }
            // Less than
            7 -> {
                if (if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } < if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                ) {
                    if (parameterModes[2] == 1) {
                        intCode[i + 3] = 1
                    } else {
                        intCode[intCode[i + 3]] = 1
                    }
                } else {
                    if (parameterModes[2] == 1) {
                        intCode[i + 3] = 0
                    } else {
                        intCode[intCode[i + 3]] = 0
                    }
                }

                i += 4
            }
            // Equals
            8 -> {
                if (if (parameterModes[0] == 1) {
                        intCode[i + 1]
                    } else {
                        intCode[intCode[i + 1]]
                    } == if (parameterModes[1] == 1) {
                        intCode[i + 2]
                    } else {
                        intCode[intCode[i + 2]]
                    }
                ) {
                    if (parameterModes[2] == 1) {
                        intCode[i + 3] = 1
                    } else {
                        intCode[intCode[i + 3]] = 1
                    }
                } else {
                    if (parameterModes[2] == 1) {
                        intCode[i + 3] = 0
                    } else {
                        intCode[intCode[i + 3]] = 0
                    }
                }

                i += 4
            }
            // Quit
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