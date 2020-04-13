package subtask3

import java.lang.StringBuilder

class Abbreviation {
    private val POSSIBLE = "YES"
    private val NOT_POSSIBLE = "NO"

    fun abbreviationFromA(a: String, b: String): String {
        var aIndex = 0
        var bIndex = 0
        val builder = StringBuilder()
        val findCharInA: (Char) -> Int = { bChar: Char ->
            while (aIndex < a.length) {
                val aChar = a[aIndex++]
                if(aChar.toUpperCase() == bChar) {
                    builder.append(aChar.toUpperCase())
                    break
                } else {
                    builder.append(aChar)
                }
            }
            aIndex
        }

        while(aIndex < a.length && bIndex < b.length) {
            val bChar = b[bIndex++]
            aIndex = findCharInA(bChar)
        }

        for((index, c) in builder.withIndex()) {
            if(c.isLowerCase()) {
                builder.setCharAt(index, '_')
            }
        }
        val result = builder.replace(Regex("_"), "")

        return if(result == b) POSSIBLE else NOT_POSSIBLE
    }
}
