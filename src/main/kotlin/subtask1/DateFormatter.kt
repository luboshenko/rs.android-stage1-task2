package subtask1

import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormatter {
    private val doesNotExists = "Такого дня не существует"
    private val ru = Locale("ru")

    fun toTextDay(day: String, month: String, year: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")
        try {
            val inputDate = "$year.$month.$day"
            val parsedDate = LocalDate.parse(inputDate, formatter)
            val formattedDate = parsedDate.format(formatter)
            if (inputDate != formattedDate) {
                return doesNotExists
            }
            val dateFormat = SimpleDateFormat("dd MMMM, EEEE", ru)
            val d = Date.from(parsedDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            return dateFormat.format(d)
        } catch (exc: Exception) {
            return doesNotExists
        }
    }
}
