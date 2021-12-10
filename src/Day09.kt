fun main(){
    val input = readInput("inputday9").map{ line -> line.split("").filter { it.isNotEmpty() }.map{ it.toInt() }}

    fun isLowPoint(x :Int, y :Int) : Boolean{
        val height = input[y][x]

        return (y == 0 || input[y - 1][x] > height) &&
                (y == input.size - 1 || input[y + 1][x] > height) &&
                (x == 0 || input[y][x - 1] > height) &&
                (x == input[y].size - 1 || input[y][x + 1] > height)
    }

    val lowPoints = mutableListOf<Point>()

    val lowest = mutableListOf<Int>()

    val size = input[0].size - 1
    //Part 1
    for(i in input.indices){
        for(j in 0..size){
            if(isLowPoint(i, j)){
                lowest.add(input[j][i])
                lowPoints.add(Point(j, i))
            }
        }
    }
    //Part 2
    val largest = mutableListOf<Int>()

    for(p in lowPoints){
        val visited = mutableListOf<Point>()
        val toVisit = mutableListOf<Point>()
        toVisit.add(p)
        while (toVisit.size > 0){
            val nextPoint = toVisit.removeAt(0)
            if(visited.contains(nextPoint)) continue

            visited.add(nextPoint)

            val row = nextPoint.x
            val col = nextPoint.y
            if (row - 1 >= 0 && input[row - 1][ col] != 9) {
                val down = Point(row - 1, col)
                if (!visited.contains(down)) toVisit.add(down)
            }
            if (row + 1 < input.size && input[row + 1][ col] != 9) {
                val up = Point(row + 1, col)
                if (!visited.contains(up)) toVisit.add(up)
            }
            if (col - 1 >= 0 && input[row][ col - 1] != 9) {
                val left = Point(row, col - 1)
                if (!visited.contains(left)) toVisit.add(left)
            }
            if (col + 1 < input[row].size && input[row][ col + 1] != 9) {
                val right = Point(row, col + 1)
                if (!visited.contains(right)) toVisit.add(right)
            }
        }
        largest.add(visited.size)
    }

    largest.sortDescending()
    println("Lowest values: ${lowest.sumOf { it + 1 }}")
    println("Largest ${largest.take(3).reduce { acc, i -> acc * i }}")
}





