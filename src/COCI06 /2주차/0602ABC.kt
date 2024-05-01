/*
 * https://dmoj.ca/problem/coci06c2p2
*/
fun main() {
    val numbers = readln().split(" ").map { it.toInt() }.sorted() //정렬로 abc 순서 보장
    numbers.any { it < 1 || it > 100 } && error("1보다 크거나 같고 100보다 작거나 같아야 함.") // 범위 확인
    val order = readln()

    val map = mapOf('A' to numbers[0], 'B' to numbers[1], 'C' to numbers[2])
    val result = order.map { map[it] }.joinToString(" ")

    println(result)
}