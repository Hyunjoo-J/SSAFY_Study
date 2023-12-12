package BFS_DFS.p16197_두동전;

import java.io.*;
import java.util.*;

public class p16197_YK {

    static class Point {
        int x1, y1;
        int x2, y2;
        int cnt;

        public Point(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static boolean[][] map;
    static int x1, x2, y1, y2;
    static int result;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        for (int i = 0; i < N; ++i) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; ++j) {
                char ch = line[j];
                if (ch == 'o') {
                    if (x1 == x2 && y1 == y2) {
                        x1 = i; y1 = j;
                    } else {
                        x2 = i; y2 = j;
                    }
                } else if (ch == '#') {
                    map[i][j] = true;
                }
            }
        }

        bfs();
        System.out.println(result);
        br.close();
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x1, y1, x2, y2, 0));
        boolean[][][][] visited = new boolean[N][M][N][M];

        while (!q.isEmpty()) {
            Point now = q.poll();

            // 횟수가 10번 초과 ...
            if (now.cnt >= 10) {
                result = -1;
                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx1 = now.x1 + dx[i];
                int ny1 = now.y1 + dy[i];
                int nx2 = now.x2 + dx[i];
                int ny2 = now.y2 + dy[i];

                // 동전이 이동하려는 방향에 칸이 없다.
                // 단 둘 다 떨어지면 안됨
                if (nx1 >= N || ny1 >= M || nx2 >= N || ny2 >= M ||
                    nx1 < 0 || ny1 < 0 || nx2 < 0 || ny2 < 0) {

                    if (nx1 >= N && nx2 >= N) continue;
                    else if (ny1 >= M && ny2 >= M) continue;
                    else if (nx1 < 0 && nx2 < 0) continue;
                    else if (ny1 < 0 && ny2 < 0) continue;

                    result = now.cnt + 1;
                    return;
                }

                // 이동하려는 칸이 벽이다.
                if (map[nx1][ny1]) {
                    nx1 = now.x1;
                    ny1 = now.y1;
                }
                if (map[nx2][ny2]) {
                    nx2 = now.x2;
                    ny2 = now.y2;
                }

                // 이동하려는 칸에 동전이 있다.
                // 벽 때문에 막힌 동전과 겹치는 경우
                if (nx1 == nx2 && ny1 == ny2) {
                    continue;
                }

                if (visited[nx1][ny1][nx2][ny2]) continue;

                visited[nx1][ny1][nx2][ny2] = true;
                q.offer(new Point(nx1, ny1, nx2, ny2, now.cnt + 1));
            }
        }

        result = -1;
    }

}
