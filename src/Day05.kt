import kotlin.math.abs
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

fun main(){
    val re = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

    val points = ArrayList<Pair<Point,Point>>()
    readInput("inputday5").forEach { line ->
        val parse =  re.matchEntire(line)
        val pointFrom = Point(parse?.groups?.get(1)?.value?.toInt()!!, parse?.groups?.get(2)?.value?.toInt()!!)
        val pointTo = Point(parse?.groups?.get(3)?.value?.toInt()!!, parse?.groups?.get(4)?.value?.toInt()!!)

        points.add(Pair(pointFrom,pointTo))
    }

    val t = measureTimeMillis { part1(points) }
    val t2 = measureTimeMillis { part2(points) }
    println("Time part 1: ${t.milliseconds} \nTime part 2 ${t2.milliseconds}")
}

fun part1(
    points: ArrayList<Pair<Point, Point>>) {
    val intersections = hashMapOf<Point, Int>()
    for (point in points) {

        val from = point.first
        val to = point.second

        if (from.y == to.y) {

            val xMin = from.x.coerceAtMost(to.x)
            val xMax = from.x.coerceAtLeast(to.x)

            for (i in xMin..xMax) {
                intersections.merge(Point(i, from.y), 1, Integer::sum)
            }

        } else if (from.x == to.x) {
            val yMin = from.y.coerceAtMost(to.y)
            val yMax = from.y.coerceAtLeast(to.y)

            for (i in yMin..yMax) {
                intersections.merge(Point(from.x, i), 1, Integer::sum)
            }
        }
    }

    var count = 0
    for(point in intersections){
        if(point.value > 1){
            count++
        }
    }
    println("Part 1 Count $count")
}

fun part2(
    points: ArrayList<Pair<Point, Point>>) {
    val intersections = hashMapOf<Point, Int>()
    for (point in points) {

        val from = point.first
        val to = point.second

        if (from.y == to.y) {

            val xMin = from.x.coerceAtMost(to.x)
            val xMax = from.x.coerceAtLeast(to.x)

            for (i in xMin..xMax) {
                intersections.merge(Point(i, from.y), 1, Integer::sum)
            }

        } else if (from.x == to.x) {
            val yMin = from.y.coerceAtMost(to.y)
            val yMax = from.y.coerceAtLeast(to.y)

            for (i in yMin..yMax) {
                intersections.merge(Point(from.x, i), 1, Integer::sum)
            }
        }else{
            val xDir = if(to.x - from.x > 0) 1 else - 1
            val yDir = if(to.y - from.y > 0) 1 else - 1
            val absVal = abs(to.x - from.x)
            for(i in 0..absVal){
                intersections.merge(Point(from.x + (xDir * i), from.y + (yDir * i)), 1, Integer::sum)
            }
        }
    }

    var count = 0
    for(point in intersections){
        if(point.value > 1){
            count++
        }
    }
    println("Part 2 Count $count")
}


class Point(var x: Int, var y: Int){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Point
        return x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

}