import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

fun main(){
    val numbers = readInput("inputday8")
    measureTimeMillis { solve(numbers) }.also { println("Time to compute ${it.milliseconds}") }
}

fun solve(numbers: List<String>) {
    val lengths = arrayListOf(2, 4, 3, 7)

    var part1 = 0
    var part2 = 0
    for (num in numbers) {
        val sp = num.split(" | ")
        val left = sp[0].split(" ")
        val right = sp[1].split(" ")

        val occurrences = mutableMapOf<String, Int>()
        for (signal in left) {
            for (char in signal) {
                occurrences.merge(char.toString(), 1, Integer::sum)
            }
        }
        var m = 1000
        for (input in right) {
            if (lengths.contains(input.length)) {
                part1 += 1
            }
            var inputSum = 0

            for (letter in input) inputSum += occurrences[letter.toString()]!!
            /*
            * For each letter, count how many times it appears in the list. For example, "a" appears 8 times, "b" appears 6 times, "c" appears 8 times etc.
            * When you see something like "cf" or "acf", add up those counts. For example, "cf" becomes 8 + 9 = 17, because "c" appears 8 times and "f" appears 9 times.
            * Now 17 is the unique id for the 1 digit, cf.
            * */
            val value = when (inputSum) {
                42 -> 0
                17 -> 1
                34 -> 2
                39 -> 3
                30 -> 4
                37 -> 5
                41 -> 6
                25 -> 7
                49 -> 8
                45 -> 9
                else -> Integer.MIN_VALUE
            }
            part2 += value * m
            m /= 10
        }

    }
    println("Part 1: $part1")
    println("Part 2: $part2")
}