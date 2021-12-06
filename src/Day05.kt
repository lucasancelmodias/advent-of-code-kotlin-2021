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

    val t = measureTimeMillis { solve(points, false) }
    val t2 = measureTimeMillis { solve(points, true) }
    println("Time part 1: ${t.milliseconds} \nTime part 2 ${t2.milliseconds}")
}

fun solve(
    points: ArrayList<Pair<Point, Point>>, part2:Boolean) {
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
        }else if(part2){
            val xDir = if(to.x - from.x > 0) 1 else - 1
            val yDir = if(to.y - from.y > 0) 1 else - 1
            val absVal = abs(to.x - from.x)
            for(i in 0..absVal){
                intersections.merge(Point(from.x + (xDir * i), from.y + (yDir * i)), 1, Integer::sum)
            }
        }
    }

    println("Count ${intersections.filter { it.value > 1 }.size}")
}

data class Point(var x: Int, var y: Int)