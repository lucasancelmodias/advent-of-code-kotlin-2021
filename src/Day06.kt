import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

fun main(){

    val input = readInput("inputday6")[0].split(",").map{ v -> v.toInt()}

    val phases = MutableList(9){BigInteger.ZERO}

    for(number in input){
        phases[number] += BigInteger.ONE
    }

    measureTimeMillis{ solve(80, phases)}.also { println("Time to compute: ${it.milliseconds}") }
    measureTimeMillis{ solve(256, phases)}.also { println("Time to compute: ${it.milliseconds}") }
    measureTimeMillis{ solveRotate(80, phases)}.also { println("Time to compute: ${it.milliseconds}") }
    measureTimeMillis{ solveRotate(256, phases)}.also { println("Time to compute: ${it.milliseconds}") }

}

fun solve(numberOfDays: Int, phases: MutableList<BigInteger>) {
    var phases1 = phases
    for (day in 1..numberOfDays) {
        val newFish = MutableList(9) { BigInteger.ZERO }

        for (phase in 8 downTo 0) {
            if (phase > 0) {
                newFish[phase - 1] = phases1[phase]
            } else if (phase == 0) {
                newFish[8] = phases1[0]
                newFish[6] += phases1[0]
            }
        }
        phases1 = newFish
    }

    println("Count ${phases1.sumOf { it }}")
}


fun solveRotate(numberOfDays: Int, phases: MutableList<BigInteger>){

    for (day in 1..numberOfDays) {
        phases[7] += phases[0]
        Collections.rotate(phases, - 1)
    }

    println("Count Rotate number of days $numberOfDays ${phases.sumOf { it }}")
}

