package BFS_DFS.p17836_공주님을구해라;

import java.io.*;
import java.util.*;
public class p17836_YK {

    static class Point {
        int x, y, gram;

        public Point(int x, int y, int gram) {
            this.x = x;
            this.y = y;
            this.gram = gram;
        }
    }

    static int N, M, T;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        System.out.println(result != -1 ? result : "Fail");
        br.close();
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0));
        visited[0][0][0] = true;

        int time = 0;
        while (!q.isEmpty()) {
            if (time > T) break;

            for (int t = 0, size = q.size(); t < size; ++t) {
                Point now = q.poll();

                if (now.x == N - 1 && now.y == M - 1) return time;

                if (map[now.x][now.y] == 2) now.gram = 1;

                for (int i = 0, gram = now.gram, nx, ny; i < 4; ++i) {
                    nx = now.x + dx[i];
                    ny = now.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (visited[nx][ny][gram]) continue;
                    if (map[nx][ny] == 1 && gram == 0) continue;

                    visited[nx][ny][gram] = true;
                    q.offer(new Point(nx, ny, gram));
                }
             }
            ++time;
        }

        return -1;
    }
}
