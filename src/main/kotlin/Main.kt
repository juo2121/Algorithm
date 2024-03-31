package org.example

import kotlin.math.abs


fun main() {
    val input = readln().toInt()
    /** 유클리드에서의 원 면적*/
    println("%.6f".format((Math.PI * input * input)))
    /** taxicab 이론에서의 원 면적 */
    println("%.6f".format((2 * input * input).toDouble()))
}

