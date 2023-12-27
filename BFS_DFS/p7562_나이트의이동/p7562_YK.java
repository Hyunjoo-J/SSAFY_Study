package BFS_DFS.p7562_나이트의이동;

import java.io.*;
import java.util.*;

public class p7562_YK {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, sx, sy, ex, ey;
    static int[] dx = {-2, -2, 2, 2, -1, -1, 1, 1};
    static int[] dy = {-1, 1, -1, 1, -2, 2, -2, 2};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[sx][sy] = true;
        q.offer(new Point(sx, sy));

        int ans = 0;
        while (!q.isEmpty()) {
            for (int k = 0, size = q.size(); k < size; ++k) {
                Point now = q.poll();
                if (now.x == ex && now.y == ey) {
                    return ans;
                }

                for (int i = 0; i < 8; ++i) {
                    int nx = now.x + dx[i], ny = now.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
            ++ans;
        }

        return -1;
    }
}
