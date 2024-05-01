
/** https://dmoj.ca/problem/coci06c1p3 */
fun main() {
    val letters = readln()
    /** 최대 길이가 15인지 검사*/
    if(letters.length > 15) error("Too long")
    /** 대문자 검사*/
    if(letters.any{it.isLowerCase()}) error("Lowercase letter")

    val res = letters.substring(1).foldIndexed(decorate(letters[0])) { idx, acc, letter ->
        /**프레임처리된 다음 문자*/
        val next = decorate(letter, idx + 2)
        (0..4).forEach { x->
            /** 이전 문자와 다음 문자 사이에 겹치는 문자가 서로 다르다면 *으로 처리*/
            if(acc[x].last() != next[x].first()) acc[x][acc[x].lastIndex] = '*'
            /** 다음 문자의 프레임을 자른 후 이전 문자의 프레임에 삽입*/
            acc[x] += next[x].sliceArray(1..4)
        }
        acc
    }.joinToString("\n") { it.joinToString("") }
    println(res)
}
/** # or * or letter가 들어갈 수 있는 좌표리스트*/
val points = listOf((0 to 2),(1 to 1),(1 to 3),(2 to 0),(2 to 2),(2 to 4),(3 to 1),(3 to 3),(4 to 2))

/** 문자를 프레임으로 감싸는 용도*/
fun decorate(letter: Char,idx:Int = 1):Array<Array<Char>> {
    /** 문자마다 2차원 배열 생성*/
    val arr = Array(5) { Array(5) { '.' } }
    points.forEach { (x, y) ->
        if(x == 2 && y == 2) arr[x][y] = letter
        /** letter의 위치가 3의 배수면 Wendy 프레임. 아닐경우 PeterPan 프레임*/
        else arr[x][y] = if(idx % 3 == 0) '*' else '#'
    }
    /** 프레임으로 감싸진 2차원 배열 반환*/
    return arr
}