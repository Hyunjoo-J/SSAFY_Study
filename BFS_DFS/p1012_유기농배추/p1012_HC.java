package BFS_DFS.p1012_유기농배추;

import java.io.*;
import java.util.*;

public class p1012_HC {

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M;
    private static int[][] graph = new int[50][50];
    private static boolean[][] visited = new boolean[50][50];
    private static Queue<Pair> queue = new ArrayDeque<>(50);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int K, X, Y;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            initialize();
            for (int i = 0; i < K; ++i) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                graph[Y][X] = 1;
            }

            int res = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (graph[i][j] == 1 && !visited[i][j]) {
                        ++res;
                        bfs(i, j);
                    }
                }
            }
            bw.write(res + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void initialize() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                graph[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }

    private static void bfs(int y, int x) {
        int nx, ny;
        queue.add(new Pair(y, x));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; ++i) {
                ny = p.y + dy[i];
                nx = p.x + dx[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;
                if (visited[ny][nx])
                    continue;
                if (graph[ny][nx] == 0)
                    continue;
                queue.add(new Pair(ny, nx));
                visited[ny][nx] = true;
            }
        }
    }
}