fun main() {
    // Input
    val input = "130254-678275"

    // Day 4 Part 1
    val range = input.split("-").map { it.toInt() }

    val options = (range[0]..range[1]).toSet().filter { isNonDecreasing(it) && hasMatchingAdjacentDigits(it) }.count()
    println(options)

    // Part 2
    val options2 =
        (range[0]..range[1]).toSet().filter { isNonDecreasing(it) && hasTwoMatchingAdjacentDigits(it) }.count()
    println(options2)

}

// Lambda functions used
fun isNonDecreasing(num: Int): Boolean =
    num.toString().toCharArray().mapIndexed { index, c -> index == 0 || c >= num.toString().toCharArray()[index - 1] }.all { it }

fun hasMatchingAdjacentDigits(num: Int): Boolean =
    num.toString().toCharArray().toList().groupBy { it }.map { it.value.size }.any { it >= 2 }

fun hasTwoMatchingAdjacentDigits(num: Int): Boolean =
    num.toString().toCharArray().toList().groupBy { it }.map { it.value.size }.any { it == 2 }