/*
 * https://dmoj.ca/problem/coci06c2p3
*/
fun main() {
    val (n1, n2) = readln().split(" ").map { it.toIntOrNull()  ?: error("invalid int")}
    val firstRow = readln().also {v -> if(v.any { it.isLowerCase() }) error("소문자는 포함되어서는 안됨.") }.reversed()
    // 두번째 개미의 줄에 첫번째 개미를 넣어줌. index 참조 오류가 나지 않도록 두번째 개미의 줄에 30개의 0을 추가해줌
    val secondRow = readln().also {v -> if(v.any { it.isLowerCase() }) error("소문자는 포함되어서는 안됨.") }
    val builder = StringBuilder(secondRow + "0".repeat(50-secondRow.size))//stringBuilder가 더 가벼움
    //.toMutableList().also { it.addAll(MutableList(30){'0'}) }
    var t = readln().toIntOrNull()?.also { t-> if(t < 0 || t> 50) error("t는 0보다 크거나 같고 50보다 작거나 같아야 함.") } ?: error("invalid int")
    var idx = n1
    if(t == 0) println(firstRow+secondRow.filter { it != '0' }.joinToString(""))
    else {
        when {
            n1 <= n2 && t >= n1 + n2 - 1 -> t = n1 + n2 - 1 // t가 n1 + n2 -1 이상인 경우 t는 n1 + n2 -1보다 클 필요가 없음 ex) n1 = 3, n2 = 5, t = 7 이나 t = 10이나 결과는 같음
            n1 > n2 && t >= n1 + n2 - 1 -> t = n1 + n2 - 1 // t가 n1 + n2 -1 이상인 경우 t는 n1 + n2 -1보다 클 필요가 없음 ex) n1 = 3, n2 = 1, t = 3 이나 t = 5이나 결과는 같음
        }
        while(t > 0 && idx > 0) { // t가 0이 될때까지 반복, 첫번째줄의 개미 이동이 모두 끝나는 경우 break ex) n1 = 2, n2 = 3, t = 4
            builder.insert(t,firstRow[idx-1]) // 첫번째줄의 개미가 이동하는 경우 두번째줄에 첫번째줄의 개미를 추가해줌.
            t--
            idx--
        }
        val res = if(idx > 0) firstRow.substring(0, idx) + secondRow.filter { it != '0' }.joinToString("") // T가 짧아서 첫번째줄의 모든 개미가 이동하지 않은 경우 이동하지 않은 개미를 붙여주기
        else secondRow.filter { it != '0' }.joinToString("")
        println(res)
    }
}
