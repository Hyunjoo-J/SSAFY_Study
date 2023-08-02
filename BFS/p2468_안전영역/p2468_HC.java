package BFS.p2468_안전영역;

import java.io.*;
import java.util.*;

public class p2468_HC {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N;
    private static int[][] graph;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int maxHeight = 0;
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, graph[i][j]);
            }
        }

        int answer = 0;
        for (int h = 0; h <= maxHeight; ++h) {
            answer = Math.max(answer, simul(h));
        }
        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int simul(int height) {
        for (int i = 0; i < N; ++i)
            Arrays.fill(visited[i], false);

        int res = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (visited[i][j])
                    continue;
                if (graph[i][j] <= height)
                    continue;
                bfs(i, j, height);
                res += 1;
            }
        }
        return res;
    }

    private static void bfs(int x, int y, int height) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        int nx, ny;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] <= height)
                    continue;
                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
