import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

fun main(){
    val crabs = readInput("inputday7")[0].split(",").map{ it.toLong()}.sorted()

    val avg = crabs.sum() / crabs.size
    val middle = crabs[crabs.size / 2]

    measureTimeMillis { println("Part 1: " + crabs.sumOf{ kotlin.math.abs(middle - it) }) }.also{ println("Time to compute ${it.milliseconds}")}

    measureTimeMillis { println(crabs.sumOf{ sumToN(avg, it) })}.also{ println("Time to compute ${it.milliseconds}")}
}


fun sumToN(avg: Long, n:Long ):Long {
    val ab = kotlin.math.abs(avg - n)
    return (ab * ab + ab) / 2
}