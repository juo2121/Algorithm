import java.util.*


/** https:/***/dmoj.ca/problem/coci06c1p4 */
fun main() {
    val queue = LinkedList<Pair<Int, Int>>() /**BFS를 위한 큐*/
    var sPos = Pair(0, 0) /**처음 고슴도치의 위치*/
    var dPos = Pair(0, 0) /**비버의 굴 위치*/
    val (R, C) = readln().split(" ").map { it.toInt() }
    if(R < 1 || R > 50 || C < 1 || C > 50) error("R, C는 1보다 크거나 같고 50보다 작거나 같아야 함.")
    val map = Array(R) { CharArray(C) }
    var errorCnt1  = 0
    var errorCnt2  = 0
    for (x in 0 until R) {
        val line = readln()
        for (y in 0 until C) {
            if(line[y] == 'D') errorCnt1++
            if(line[y] == 'S') errorCnt2++
            if(errorCnt1 == 2 || errorCnt2 == 2) error("D, S는 각각 하나씩만 존재해야 함.")
            when(line[y]) {
                'D' -> dPos = Pair(x, y) /**비버의 굴 위치*/
                'S' -> sPos = Pair(x, y) /**고슴도치의 시작 위치*/
                '*' -> queue.add(Pair(x, y)) /**물의 위치들 큐에 추가*/
                '.' -> {}
                'X' -> {}
                else -> error("input은 D, S, *, ., X 만 가능함")
            }
            map[x][y] = line[y]
        }
    }

    var time = 0 /**처음은 0분*/
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val wRoute = Array(R) { IntArray(C) { -1 } } /**각 좌표에는 물이 도달할 수 있는 최단시간을 기록*/
    val sRoute = Array(R) { IntArray(C) { -1 } } /**각 좌표에는 고슴도치가 갈 수 있는 최단시간을 기록*/
    while (queue.size > 0) {
        val size = queue.size
        for (i in 0 until size) { /**매분마다 물의 이동 위치를 계산*/
            val (x, y) = queue.remove()
            if (time == 0) wRoute[x][y] = 0 /**물의 시작점은 0*/
            for (k in 0 until 4) {
                val nx = x + dx[k] /** 다음 x 좌표 계산*/
                val ny = y + dy[k] /** 다음 y 좌표 계산*/
                if (nx in 0 until R && ny in 0 until C && wRoute[nx][ny] == -1 && (map[nx][ny] == '.' || map[nx][ny] == 'S')) { /**물이 찰 수 있는 조건*/
                    wRoute[nx][ny] = time + 1 /**최단시간 : 현재시간 + 1분*/
                    queue.add(Pair(nx, ny)) /**다음 이동 위치를 큐에 추가*/
                }
            }
        }
        time++
    }

    time = 0 /**다시 초기화*/
    queue.clear()
    queue.add(sPos) /**고슴도치의 시작점*/
    sRoute[sPos.first][sPos.second] = 0 /**고슴도치의 시작점은 0*/
    while (queue.size > 0) {
        val size = queue.size
        for (i in 0 until size) {
            val (x, y) = queue.remove()
            for (k in 0 until 4) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until R && ny in 0 until C && sRoute[nx][ny] == -1 && map[nx][ny] != 'X' && (sRoute[x][y] + 1 < wRoute[nx][ny] || wRoute[nx][ny] == -1)) { /***/ 고슴도치가 이동할 수 있는 조건
                    sRoute[nx][ny] = time + 1 /**고슴도치가 도달할 수 있는 최단 시간을 기록*/
                    queue.add(Pair(nx, ny)) /**다음 이동 위치를 큐에 추가*/
                }
            }
        }
        time++ /**1분 증가*/
    }
    if(sRoute[dPos.first][dPos.second] == -1) println("KAKTUS")/** 고슴도치가 비버의 굴에 도달할 수 없는 경우 */
    else println(sRoute[dPos.first][dPos.second]) /**고슴도치가 비버의 굴에 도달할 수 있는 경우*/
}