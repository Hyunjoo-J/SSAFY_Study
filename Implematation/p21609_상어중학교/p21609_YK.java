package Implematation.p21609_상어중학교;

import java.io.*;
import java.util.*;

public class p21609_YK {

    static class Block {
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // N, M, 기준블럭x, 기준블럭y, 무지개블럭수, 블럭그룹수, 그룹번호
    static int N, M, sx, sy, rainbow, size, k;
    static int[][] map, tmpMap;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Block> q = new ArrayDeque<>();

    static final int EMPTY = -9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tmpMap = new int[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (true) {
            sx = -1; sy = -1;
            rainbow = 0; size = 0;
            cleanVisited();

            k = -1; int tmpK = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (visited[i][j] > 0 || map[i][j] <= 0) continue;
                    bfs(i, j, ++tmpK);
                }
            }
            if (size < 2) break;
            result += size * size;

            cleanVisited();
            bfs(sx, sy, k);

            gravity();

            cleanVisited();
            rotate();
            gravity();
        }

        System.out.println(result);
        br.close();
    }

    private static void cleanVisited() {
        for (int i = 0; i < N; ++i) {
            Arrays.fill(visited[i], 0);
        }
    }

    private static void rotate() {
        for (int i = 0; i < N; ++i) {
            Arrays.fill(tmpMap[i], EMPTY);
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                tmpMap[i][j] = map[j][N - 1 - i];
            }
        }

        for (int i = 0; i < N; ++i) {
            map[i] = Arrays.copyOf(tmpMap[i], N);
        }
    }

    private static void gravity() {
        for (int i = 0; i < N; ++i) {
            Arrays.fill(tmpMap[i], EMPTY);
        }

        for (int y = 0, tx; y < N; ++y) {
            tx = N - 1;
            for (int x = N - 1; x >= 0; --x) {
                if (map[x][y] == EMPTY) continue;
                if (visited[x][y] == k) continue;
                if (map[x][y] == -1) {
                    tmpMap[x][y] = -1;
                    tx = x - 1;
                    continue;
                }
                tmpMap[tx--][y] = map[x][y];
            }
        }

        for (int i = 0; i < N; ++i) {
            map[i] = Arrays.copyOf(tmpMap[i], N);
        }
    }

    private static void bfs(int x, int y, int tmpK) {
        int tmpRainbow = 0;
        int tmpSize = 0;
        int tx = x, ty = y;
        int color = map[x][y];

        q.offer(new Block(x, y));
        visited[x][y] = tmpK;

        while (!q.isEmpty()) {
            Block b = q.poll();

            ++tmpSize;
            if (map[b.x][b.y] == 0) ++tmpRainbow;
            else {
                if ((tx > b.x) || (tx == b.x && ty > b.y)) {
                    tx = b.x;
                    ty = b.y;
                }
            }

            for (int i = 0, nx, ny; i < 4; ++i) {
                nx = b.x + dx[i]; ny = b.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] != 0 && visited[nx][ny] > 0) continue;
                if (map[nx][ny] == 0 && visited[nx][ny] == tmpK) continue;
                if (map[nx][ny] < 0) continue;
                if (map[nx][ny] != 0 && map[nx][ny] != color) continue;

                visited[nx][ny] = tmpK;
                q.offer(new Block(nx, ny));
            }
        }

        if (tmpSize < size) return;
        if (tmpSize == size && tmpRainbow < rainbow) return;
        if (tmpSize == size && tmpRainbow == rainbow && tx < sx) return;
        if (tmpSize == size && tmpRainbow == rainbow && tx == sx && ty <= sy) return;

        size = tmpSize;
        rainbow = tmpRainbow;
        sx = tx; sy = ty;
        k = tmpK;
    }
}
