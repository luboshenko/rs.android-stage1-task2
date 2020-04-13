package subtask4

import kotlin.text.StringBuilder

class Pangram {
    private val alphabet = 'a'..'z'
    private val vowels = "aeiouy"
    private val consonants = "bcdfghjklmnpqrstvwxz"

    fun getResult(inputString: String): String {
        val strings = extractWords(inputString)

        val countedWords = if(isPangram(inputString)) countAndCapitalizeLetters(strings, ::isVowel)
            else countAndCapitalizeLetters(strings, ::isConsonant)

        countedWords.sortWith(WordsComparator())
        val result = StringBuilder()
        for(word in countedWords) {
            result.append("${word.countLetters}${word.word} ")
        }
        return result.toString().trimEnd()
    }

    private fun countAndCapitalizeLetters(words: List<String>, checker: (c: Char) -> Boolean): ArrayList<Word> {
        val result = ArrayList<Word>(words.size)
        for ((index, word) in words.withIndex()) {
            result.add(Word(index, countLettersInWord(word, checker), capitalizeLetters(word, checker)))
        }
        return result
    }

    private fun extractWords(text: String): List<String> {
        val notFound = -1
        val words = ArrayList<String>()
        var from = 0
        var to = 0
        val searchStart: () -> Int = { ->
            var i = from
            while (i < text.length && text[i].isWhitespace()) {
                i++
            }
            if(i < text.length) i else notFound
        }
        val searchEnd: () -> Int = { ->
            var i = from
            while (i < text.length && !text[i].isWhitespace()) {
                i++
            }
            i
        }
        while (from != notFound && to != notFound) {
            from = searchStart()
            if(from == notFound) {
                break
            }
            to = searchEnd()
            words.add(text.substring(startIndex = from, endIndex = to))
            from = to + 1
        }
        return words
    }

    private fun capitalizeLetters(word: String, checker: (c: Char) -> Boolean): String {
        val builder = StringBuilder(word.length)
        for (c in word) {
            builder.append(if(checker(c)) c.toUpperCase() else c)
        }
        return builder.toString()
    }

    private fun countLettersInWord(word: String, checker: (c: Char) -> Boolean): Int {
        var counter = 0
        for(c in word) {
            if(checker(c)) {
                counter++
            }
        }
        return counter
    }

    private fun isVowel(c: Char) = vowels.contains(c, true)

    private fun isConsonant(c: Char) = consonants.contains(c, true)

    private fun isPangram(sentence: String): Boolean {
        for (c in alphabet) {
            if(!sentence.contains(c, ignoreCase = true)) {
                return false
            }
        }
        return true
    }

    class Word(val index: Int, val countLetters: Int, val word: String)

    class WordsComparator() : Comparator<Word> {
        override fun compare(p0: Word?, p1: Word?): Int {
            if(p0 != null && p1 != null) {
                if(p0.countLetters == p1.countLetters) {
                    return p0.index.compareTo(p1.index)
                }
                return p0.countLetters.compareTo(p1.countLetters)
            }
            else if(p0 == null && p1 != null) {
                return -1
            }
            else if(p0 != null && p1 == null) {
                return 1
            }
            else {
                return 0
            }
        }
    }
}
