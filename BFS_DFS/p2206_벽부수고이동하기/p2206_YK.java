package BFS_DFS.p2206_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2206_YK {
    static int N, M, result = Integer.MAX_VALUE;
    static int[][] input;
    static boolean[][][] visited;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};

    static class Point {
        int x;
        int y;
        int wall;
        int dist;
        Point (int x, int y, int wall, int dist) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                input[i][j] = line.charAt(j) - '0';
            }
        }

        visited[0][0][0] = true;
        bfs();
        bw.write(String.valueOf(result != Integer.MAX_VALUE ? result : -1));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, 1));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                result = Math.min(result, now.dist);
                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny][now.wall]) continue;
                if (input[nx][ny] == 1 && now.wall != 0) continue;

                if (input[nx][ny] == 1) {
                    visited[nx][ny][1] = true;
                    q.offer(new Point(nx, ny, 1, now.dist + 1));
                }
                else {
                    visited[nx][ny][now.wall] = true;
                    q.offer(new Point(nx, ny, now.wall, now.dist + 1));
                }
            }
        }
    }
}