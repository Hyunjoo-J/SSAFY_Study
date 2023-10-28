package BFS_DFS.p14940_쉬운최단거리;

import java.io.*;
import java.util.*;

public class p14940_HC {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][m];
        int[][] visited = new int[n][m];
        int sx = -1, sy = -1;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited[i], -1);
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    sx = i;
                    sy = j;
                } else if (graph[i][j] == 0) {
                    visited[i][j] = 0;
                }
            }
        }

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(sx, sy));
        visited[sx][sy] = 0;

        int nx, ny;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (graph[nx][ny] == 0)
                    continue;
                if (visited[nx][ny] > -1)
                    continue;
                visited[nx][ny] = visited[p.x][p.y] + 1;
                queue.add(new Pair(nx, ny));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                sb.append(visited[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

        br.close();
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}