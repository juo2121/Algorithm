import java.util.*


/** https://dmoj.ca/problem/coci06c1p4 */
fun main() {
    val queue = LinkedList<Pair<Int, Int>>() // BFS를 위한 큐
    var sPos = Pair(0, 0) // 처음 고슴도치의 위치
    var dPos = Pair(0, 0) // 비버의 굴 위치
    val (R, C) = readln().split(" ").map { it.toInt() }
    val map = Array(R) { CharArray(C) }
    for (x in 0 until R) {
        val line = readln()
        for (y in 0 until C) {
            when(line[y]) {
                'D' -> dPos = Pair(x, y) // 비버의 굴 위치
                'S' -> sPos = Pair(x, y) // 고슴도치의 시작 위치
                '*' -> queue.add(Pair(x, y)) // 물의 위치들 큐에 추가
            }
            map[x][y] = line[y]
        }
    }

    var time = 0
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val wRoute = Array(R) { IntArray(C) { -1 } } // 물의 최단시간 이동경로
    val sRoute = Array(R) { IntArray(C) { -1 } } // 고슴도치의 최단시간 이동경로
    while (queue.size > 0) {
        val size = queue.size
        for (i in 0 until size) { // 매분마다 물의 이동 위치를 계산
            val (x, y) = queue.remove()
            if (time == 0) wRoute[x][y] = 0  // 물의 시작점은 0
            for (k in 0 until 4) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until R && ny in 0 until C && wRoute[nx][ny] == -1 && (map[nx][ny] == '.' || map[nx][ny] == 'S')) { // 물이 찰 수 있는 조건
                    wRoute[nx][ny] = time + 1 // 물이 도달할 수 있는 최단 시간을 기록
                    queue.add(Pair(nx, ny)) // 다음 이동 위치를 큐에 추가
                }
            }
        }
        time++
    }

    time = 0 // 다시 초기화
    queue.clear()
    queue.add(sPos) // 고슴도치의 시작점
    sRoute[sPos.first][sPos.second] = 0 // 고슴도치의 시작점은 0
    while (queue.size > 0) {
        val size = queue.size
        for (i in 0 until size) {
            val (x, y) = queue.remove()
            for (k in 0 until 4) {
                val nx = x + dx[k]
                val ny = y + dy[k]
                if (nx in 0 until R && ny in 0 until C && sRoute[nx][ny] == -1 && map[nx][ny] != 'X' && (sRoute[x][y] + 1 < wRoute[nx][ny] || wRoute[nx][ny] == -1)) { // 고슴도치가 이동할 수 있는 조건
                    sRoute[nx][ny] = time + 1 // 고슴도치가 도달할 수 있는 최단 시간을 기록
                    queue.add(Pair(nx, ny)) // 다음 이동 위치를 큐에 추가
                }
            }
        }
        time++
    }
    if(sRoute[dPos.first][dPos.second] == -1) println("KAKTUS")
    else println(sRoute[dPos.first][dPos.second])
}