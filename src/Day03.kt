fun main(){
    println("Part 1 " + part1(readInput("inputday3")))
    println("Part 2 " +
            part2(readInput("inputday3"), true) *
            part2(readInput("inputday3"), false))

}


fun part1(lines: List<String>): Int{
    var gamma = ""
    var epsilon = ""


    for(i in 0 until lines[0].length){
        val (zeros, ones) = getMostCommon(lines as MutableList<String>, i)
        if(zeros >= ones){
            gamma += "0"
            epsilon += "1"
        }else {
            gamma += "1"
            epsilon += "0"
        }
    }

    return gamma.toInt(2) * epsilon.toInt(2)
}


fun part2(input: List<String>, common: Boolean):Int{
    var lines = input
    for(i in 0 until lines[0].length){

        val (zeros, ones) = getMostCommon(lines as MutableList<String>, i)

        lines = if(common){
            if(ones >= zeros){
                lines.filter{ line -> line[i].toString() == "1"}
            }else {
                lines.filter{ line -> line[i].toString() == "0"}
            }
        }else{
            if(ones >= zeros || ones == zeros){
                lines.filter{ line -> line[i].toString() == "0"}
            }else{
                lines.filter{ line -> line[i].toString() == "1"}
            }
        }
        if(lines.size == 1) break
    }

    return lines[0].toInt(2)
}

fun getMostCommon(lines: MutableList<String>, column: Int):Pair<Int,Int>{
    var zeros = 0
    var ones = 0

    for(line in lines){
        if(line[column].toString() == "0"){
            zeros++
        }else {
            ones++
        }
    }
    return Pair(zeros, ones)
}