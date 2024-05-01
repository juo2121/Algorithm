
fun main() {
    val n = readln().toIntOrNull()?.also{ if(it !in 1..1000) error("n not in range 1..1000") } ?: error("invalid int of n")
    val c = readln().toIntOrNull()?.also{ if(it !in 0..10000) error("c not in range 0..10000") } ?: error("invalid int of c")

    val startTime = System.currentTimeMillis()
    (1..c).forEachIndexed { idx, _ ->
        memo[idx + 1] = memo[idx]?.swap(n-1) ?: (1..n).joinToString("").also {
            cache.add(it)
            memo[idx] = it
        }.swap(n-1)
    }
    println("time : ${System.currentTimeMillis() - startTime}ms")

    (0..<memo.size).forEach { println("$it : ${memo[it]}") }
    println(memo[c]?.split(",")?.size)
}
val memo = mutableMapOf<Int,String>()
val cache = mutableListOf<String>() // 중복체크용
//자리바꿈함수
fun String.swap(size:Int):String {
    val builder = StringBuilder()
    println("swap : $this")
    split(",").forEach {  str ->
        println("str : $str")
        str.forEachIndexed {idx,_ ->
            val newStr = StringBuilder(str)
            if (idx < size && str[idx] < str[idx + 1]) {
                val temp = str[idx]
                newStr[idx] = str[idx + 1]
                newStr[idx + 1] = temp
                if("$newStr" !in cache) {
                    cache.add("$newStr")
                    builder.append(",$newStr")
                }
            }
        }
    }
    return "${builder.drop(1)}"
}
