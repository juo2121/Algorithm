
/** https://dmoj.ca/problem/coci06c1p1 */
fun main(){
    val res = (1..10).fold(mutableSetOf<Int>()){acc, _->
        acc.add(readLine()!!.toInt() % 42)
        acc
    }.size
    println(res)
}