package BFS_DFS.p2583_영역구하기;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2583_YK {

    static int M, N, K, result;
    static int[][] input;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (x1 < 0 || x1 > N) continue;
            if (y1 < 0 || y1 > M) continue;
            if (x2 < 0 || x2 > N) continue;
            if (y2 < 0 || y2 > M) continue;

            input[x1][y1] += 1;
            input[x1][y2] -= 1;
            input[x2][y1] -= 1;
            input[x2][y2] += 1;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                input[i][j + 1] += input[i][j];
            }
        }


        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                input[j + 1][i] += input[j][i];
            }
        }

        int[] rooms = new int[M * N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (input[i][j] != 0 || visited[i][j]) continue;
                rooms[result++] = bfs(i, j);
            }
        }

        rooms = Arrays.copyOf(rooms, result);
        Arrays.sort(rooms);
        sb.append("\n");
        for (int i = 0; i < result; i++) {
            sb.append(rooms[i]).append(" ");
        }

        bw.write(String.valueOf(result));
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int bfs(int x, int y) {
        visited[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        int space = 0;
        while (!q.isEmpty()) {
            Point now = q.poll();
            space++;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (input[nx][ny] != 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }

        return space;
    }
}
