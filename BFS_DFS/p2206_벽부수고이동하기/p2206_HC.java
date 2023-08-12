package BFS_DFS.p2206_벽부수고이동하기;

import java.io.*;
import java.util.*;

public class p2206_HC {

    class Pair {
        int x, y, crush;

        public Pair(int x, int y, int crush) {
            this.x = x;
            this.y = y;
            this.crush = crush;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M;
    private static int[][] graph;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }
        bw.write(String.valueOf(bfs()));

        bw.flush();
        bw.close();
        br.close();
    }

    private int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0, 0));
        int[][][] visited = new int[N][M][2];
        visited[0][0][0] = 1;
        int nx, ny;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if (graph[nx][ny] == 0) {
                    if (visited[nx][ny][p.crush] != 0)
                        continue;
                    queue.add(new Pair(nx, ny, p.crush));
                    visited[nx][ny][p.crush] = visited[p.x][p.y][p.crush] + 1;
                } else if (p.crush == 0) {
                    if (visited[nx][ny][1] == 0) {
                        queue.add(new Pair(nx, ny, 1));
                        visited[nx][ny][1] = visited[p.x][p.y][0] + 1;
                    }
                }
            }
        }

        if (visited[N-1][M-1][0] == 0 && visited[N-1][M-1][1] == 0) {
            return -1;
        } else if (visited[N-1][M-1][0] == 0) {
            return visited[N-1][M-1][1];
        } else if (visited[N-1][M-1][1] == 0) {
            return visited[N-1][M-1][0];
        } else {
            return Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]);
        }
    }

    public static void main(String[] args) throws Exception {
        new p2206_HC().solution();
    }
}