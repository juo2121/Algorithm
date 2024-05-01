
/** https://dmoj.ca/problem/coci06c1p2 */
fun main() {
    val input = readln().toIntOrNull() ?: throw error("input은 정수여야 합니다.")
    if(input < 0 || input > 10000) throw error("input은 0보다 크거나 같고 10000보다 작거나 같아야 합니다.")
    /** 유클리드에서의 원 면적*/
    println("%.6f".format((Math.PI * input * input)))
    /** taxicab 이론에서의 원 면적 */
    println("%.6f".format((2 * input * input)))
}