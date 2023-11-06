package BFS_DFS.p14940_쉬운최단거리;

import java.io.*;
import java.util.*;
public class p14940_YK {

    static int N, M, ex, ey;
    static int[][] map, result;
    static Queue<Point> q;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        result = new int[N][M];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    ex = i;
                    ey = j;
                }
            }
        }

        bfs(ex, ey);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (result[i][j] == 0 && map[i][j] == 1) {
                    result[i][j] = -1;
                }
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        q.add(new Point(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int nx, ny;
            for (int i = 0; i < 4; ++i) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;
                result[nx][ny] = cur.cnt + 1;
                q.offer(new Point(nx, ny, cur.cnt + 1));
                visited[nx][ny] = true;
            }
        }
    }
}
