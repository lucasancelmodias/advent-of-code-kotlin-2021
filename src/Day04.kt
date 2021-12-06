import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTimedValue

fun main(){


    val lines = readInput("ijp4lu")
    val values = lines[0].split(",").map{v -> v.toInt()}
    val l = lines.subList(2, lines.size).map { s -> s.trim() }.filter{it.isNotEmpty()}

    val tables = createTable(l)

    val t = measureTimeMillis { part1(values, tables) }
    val t2 = measureTimeMillis {   part2(values, tables.toMutableList()) }

    println("Time part 1: ${t.milliseconds}")
    println("Time part 2: ${t2.milliseconds}")

}

fun part1(values: List<Int>, tables: MutableList<Table>) {
    var winnerNumber = Int.MIN_VALUE
    var winnerTable = Table(mutableListOf())

    first@ for (number in values) {

         for (table in tables) {
            table.markNumber(number)
            if (table.won()) {

                winnerNumber = number
                winnerTable = table
                break@first

            }
        }
    }

    println("First Winner Number $winnerNumber")
    println("Sum ${winnerTable.sum()}")
    println("Final Value Part 1 ${winnerNumber * winnerTable.sum()}")
    println("#########################")


}
fun part2(numbers: List<Int>, boards: MutableList<Table>) {
    var b = boards
    val (lastWinningBoard, finalNumber) = numbers.firstNotNullOf { number ->
        val (winningBoards, losingBoards) = b.partition { it.markNumber(number).won() }

        if (losingBoards.isEmpty()) {
            Pair(winningBoards.last(), number)
        } else {
            null.also { b = losingBoards as MutableList<Table> }
        }
    }
    println("Last Winner Number $finalNumber")
    println("Sum ${lastWinningBoard.sum()}")
    println("Last Winner Part 2 ${lastWinningBoard.sum() * finalNumber}")
}


fun createTable(lines: List<String>): MutableList<Table>{

    val tables = mutableListOf<Table>()

    for(index in 4..lines.size + 1 step 5 ){
        val values = mutableListOf<List<Cell>>()

        for(n in 4 downTo 0){

            val line = lines[index - n].split(" ").filter { it.isNotEmpty() }.map { e -> Cell(false, e.toInt()) }

            values.add(line)

        }
        val table = Table(values)
        tables.add(table)

    }

    return tables
}

data class Table(var values: MutableList<List<Cell>>){


    fun won():Boolean{
        val wonLines by lazy {
            values.any{ row -> row.all{ it.marked}}
        }

        val wonColumns by lazy {
            values.indices.any { column -> values.all { row -> row[column].marked}}
        }

        return wonColumns || wonLines
    }

    fun markNumber(number: Int) = apply{
        values.map{ item ->
            item.map{ pair ->

                if(!pair.marked) pair.marked = (pair.value == number)}}
    }

    fun sum():Int{
        return values.flatten().filter { !it.marked }.sumOf { cell -> cell.value }
    }
}

data class Cell(var marked: Boolean, var value: Int)