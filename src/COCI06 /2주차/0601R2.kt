/*
 * https://dmoj.ca/problem/coci06c2p1
*/
fun main() {
    val (r1, s) = readIn().split(" ").map { it.toInt() }
    if(r1 < -1000 || r1 > 1000 || s < -1000 || s > 1000) error("r1, s는 -1000보다 크거나 같고, 1000보다 작거나 같아야 함.")
    println(s * 2 - r1)
}
