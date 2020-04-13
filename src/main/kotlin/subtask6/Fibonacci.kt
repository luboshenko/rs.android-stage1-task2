package subtask6

class Fibonacci {

    fun productFibonacciSequenceFor(n: Int): IntArray {
        var f1 = f(0)
        var f2 = f(1)
        var prod = 0
        var i = 1
        while (prod < n) {
            f1 = f2
            f2 = f(++i)
            prod = f1 * f2
        }
        return intArrayOf(f1, f2, if(prod == n) 1 else 0)
    }

    fun f(n: Int): Int {
        if(n == 0) return 0
        if(n == 1) return 1
        var f1 = 0
        var f2 = 1
        var f3 = 0
        for (i in 1 until n) {
            f3 = f1 + f2
            f1 = f2
            f2 = f3
        }
        return f3
    }
}
