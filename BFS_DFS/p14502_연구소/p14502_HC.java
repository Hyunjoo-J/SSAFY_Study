package BFS_DFS.p14502_연구소;

import java.io.*;
import java.util.*;

public class p14502_HC {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M, NM;
    private static int answer;
    private static int[][] graph;
    private static Queue<Pair> queue;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        NM = N * M;
        graph = new int[N][M];
        visited = new boolean[N][M];
        queue = new ArrayDeque<>(10);
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, int depth) {
        if (depth == 3) {
            answer = Math.max(answer, simul());
            return;
        }
        int x, y;
        for (int i = idx; i < NM; ++i) {
            x = i / M;
            y = i % M;
            if (graph[x][y] > 0)
                continue;
            graph[x][y] = 1;
            dfs(i + 1, depth + 1);
            graph[x][y] = 0;
        }
    }

    private static int simul() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (graph[i][j] == 2 && !visited[i][j])
                    bfs(i, j);
            }
        }

        int res = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (graph[i][j] == 0 && !visited[i][j])
                    ++res;
                visited[i][j] = false;
            }
        }
        return res;
    }

    private static void bfs(int x, int y) {
        int nx, ny;
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] > 0)
                    continue;
                visited[nx][ny] = true;
                queue.add(new Pair(nx, ny));
            }
        }
    }
}