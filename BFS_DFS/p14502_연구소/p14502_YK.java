package BFS_DFS.p14502_연구소;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p14502_YK {

    static int N, M, result, vCnt;
    static int[][] input;
    static boolean[][] visited;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int[][] walls = new int[3][2];
    static int[][] viruses = new int[10][2];

    static class Point {
        int x;
        int y;
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (input[i][j] == 2) {
                    viruses[vCnt][0] = i;
                    viruses[vCnt++][1] = j;
                }
            }
        }

        combination(0, 0, 0);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int cnt, int sx, int sy) {
        if (cnt == 3) {
            int[][] copy = new int[N][M];
            for (int i = 0; i < N; i++) {
                copy[i] = Arrays.copyOf(input[i], M);
            }
            for (int[] w : walls) {
                copy[w[0]][w[1]] = 1;
            }

            for (int i = 0; i < vCnt; ++i) {
                visited = new boolean[N][M];
                bfs(copy, viruses[i][0], viruses[i][1]);
            }

            int tmp = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (copy[i][j] == 0) ++tmp;
                }
            }

            result = Math.max(result, tmp);
            return;
        }

        for (int i = sx; i < N; ++i) {
            sy = i == sx ? sy : 0;
            for (int j = sy; j < M; ++j) {
                if (input[i][j] != 0) continue;
                walls[cnt][0] = i;
                walls[cnt][1] = j;
                combination(cnt + 1, i, j + 1);
            }
        }
    }

    private static void bfs(int[][] arr, int x, int y) {
        visited[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny] || arr[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                arr[nx][ny] = 2;
                q.offer(new Point(nx, ny));
            }
        }
    }
}
