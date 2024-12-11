import java.io.File
import kotlin.math.abs

fun main() {
    val filePath = "./resources/input.txt"

    /* PART 1 */

    var numberOfValidReports = 0
    File(filePath).forEachLine {
        val reportAsString = it.split(" ")
        val report = reportAsString.map{ it.toInt() }
        if (checkIfReportValid(report)) {
            numberOfValidReports++
        }
    }

    println("part 1 : " + numberOfValidReports)

    /* PART 2 */

    numberOfValidReports = 0
    File(filePath).forEachLine {
        val reportAsString = it.split(" ")
        val report = reportAsString.map{ it.toInt() }
        if (checkIfReportValid(report)) {
            numberOfValidReports++
        } else if (doubleCheckForValidReport(report)) {
            numberOfValidReports++
        }
    }

    println("part 2 : " + numberOfValidReports)
}

fun doubleCheckForValidReport(report: List<Int>): Boolean {
    for(i in report.indices) {
        val modifiedReport = report.filterIndexed { index, _ -> index != i }
        if (checkIfReportValid(modifiedReport)) {
            return true
        }
    }
    return false
}

fun checkIfReportValid(report: List<Int>): Boolean {
    val correctComparisonScore = report.zipWithNext().all {(a, b) -> abs(a - b) in 1..3 }

    val descendingOrder = report.zipWithNext().all {(a, b) -> a > b}
    val ascendingOrder = report.zipWithNext().all {(a, b) -> a < b}

    if ((descendingOrder || ascendingOrder) && correctComparisonScore)
        return true
    return false
}
