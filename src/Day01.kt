fun main() {
    fun part1(input: List<Int>): Int {
        var counter = 0
        for( index in 1 until input.size){
            if(input[index] > input[index - 1]) counter++
        }
        return counter
    }

    fun part2(input: List<Int>): Int {
        var counter = -1
        var currentSum = Int.MIN_VALUE
        for(index in 0 until (input.size - 2)){
            val sum = input[index] + input[index + 1] +  input[index + 2]
            if (sum > currentSum) ++counter
            currentSum = sum
        }

        return counter
    }

    val input = readInput("input")
    println(part1(input))
    println(part2(input))
}
