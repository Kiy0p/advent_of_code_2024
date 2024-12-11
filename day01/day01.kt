import java.io.File
import kotlin.math.abs

fun main() {
    /* PART 1 */
    val filePath = "./resources/input.txt"
    val lists = Pair(mutableListOf<Int>(), mutableListOf<Int>())

    File(filePath).forEachLine {
        val lineList = it.split("   ").map{it.toInt()}
        lists.first.add(lineList[0]) 
        lists.second.add(lineList[1])
    }

    lists.first.sortDescending()
    lists.second.sortDescending()

    var numberOfLocations = 0
    lists.first.forEachIndexed{ index, number -> numberOfLocations += abs(number - lists.second[index]) }

    println("part 1 : " + numberOfLocations)

    /* PART 2 */

    var result2 = lists.first.fold(0){ acc, number -> acc + number * lists.second.count{it == number} }

    println("part 2 : " + result2)
}