package BFS_DFS.p17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유;

import java.io.*;
import java.util.*;

public class p17129_HC {

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] A = new int[n][m];
        int sx = -1, sy = -1;
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                A[i][j] = line.charAt(j) - '0';
                if (A[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(sx, sy));
        int[][] visited = new int[n][m];
        visited[sx][sy] = 1;

        int nx, ny, dist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (A[p.x][p.y] > 2) {
                dist = visited[p.x][p.y] - 1;
                break;
            }

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (visited[nx][ny] > 0)
                    continue;
                if (A[nx][ny] == 1)
                    continue;
                queue.add(new Pair(nx, ny));
                visited[nx][ny] = visited[p.x][p.y] + 1;
            }
        }

        if (dist == Integer.MAX_VALUE) {
            System.out.println("NIE");
        } else {
            System.out.println("TAK\n" + dist);
        }
        br.close();
    }
}