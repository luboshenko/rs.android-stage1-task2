package subtask2

class TimeConverter {
    private val numbers = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
        6 to "six", 7 to "seven", 8 to "eight", 9 to "nine", 10 to "ten", 11 to "eleven",
        12 to "twelve", 13 to "thirteen", 14 to "fourteen", 15 to "fifteen", 16 to "sixteen",
        17 to "seventeen", 18 to "eighteen", 19 to "nineteen",
        20 to "twenty", 30 to "thirty",  40 to "forty",  50 to "fifty")

    private fun getHours(hours: Int) = this.numbers[hours]

    private fun getMinutes(minutes: Int): String {
        if(minutes in 1..19) {
            return "${numbers[minutes]} minute${if(minutes > 1) "s" else ""}"
        }
        val rest = minutes % 10
        if(rest == 0) {
            return "${numbers[minutes]} minutes"
        }
        return "${numbers[minutes-rest]} ${numbers[rest]} minutes"
    }

    fun toTextFormat(hour: String, minute: String): String {
        val intHours = hour.toInt()

        return when(val intMinutes = minute.toInt()) {
            0 -> "${getHours(intHours)} o' clock"
            in 1..14, in 16..29 -> "${getMinutes(intMinutes)} past ${getHours(intHours)}"
            15 -> "quarter past ${getHours(intHours)}"
            30 -> "half past ${getHours(intHours)}"
            in 31..44, in 46..59 -> "${getMinutes(60-intMinutes)} to ${getHours(intHours+1)}"
            45 -> "quarter to ${getHours(intHours + 1)}"
            else -> ""
        }
    }
}
