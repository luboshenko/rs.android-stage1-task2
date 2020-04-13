package subtask5

import java.lang.StringBuilder
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.reflect.KClass

class Blocks {

    fun getData(blockA: Array<Any>, blockB: KClass<*>): Any = when (blockB) {
            String::class -> concatenate(blockA)
            Int::class -> summarize(blockA)
            else -> getTheClosest(blockA)
        }

    private fun concatenate(data: Array<Any>): String {
        val builder = StringBuilder()
        data.forEach { if(it is String) builder.append(it) }
        return builder.toString()
    }

    private fun summarize(data: Array<Any>): Int {
        var sum = 0
        data.forEach { if(it is Int) sum += it }
        return sum
    }

    private fun getTheClosest(data: Array<Any>): String {
        val currentTime = Date.valueOf(LocalDate.now()).time
        var minDifference: Long = abs(currentTime - Date.valueOf(LocalDate.MIN).time)
        var theClosestDate: LocalDate = LocalDate.MIN

        data.forEach { if(it is LocalDate) {
            val difference = abs(currentTime - Date.valueOf(it).time)
            if(difference < minDifference) {
                minDifference = difference
                theClosestDate = it
            }
        } }
        return theClosestDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}
