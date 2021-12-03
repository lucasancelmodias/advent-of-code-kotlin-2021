fun main(){
    fun part1(values: List<String>): Int{
        var horizontal = 0
        var depth = 0

        values.forEach { line ->
            val split = line.split(" ")
            val key = split[0]
            val value = split[1].toInt()

            when(key){
                "forward" -> horizontal += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }

        return horizontal * depth
    }

    fun part2(values: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        values.forEach { line ->
            val split = line.split(" ")
            val key = split[0]
            val value = split[1].toInt()

            when (key) {
                "forward" -> {
                    horizontal += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        return horizontal * depth
    }


    val input = readInput("inputday2")
    println(part1(input))
    println(part2(input))
}