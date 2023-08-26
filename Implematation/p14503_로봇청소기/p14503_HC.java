package Implematation.p14503_로봇청소기;

import java.io.*;
import java.util.*;

public class p14503_HC {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] graph = new boolean[N][M];
        boolean[][] completed = new boolean[N][M];
        int x, y, dir;

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                graph[i][j] = st.nextToken().charAt(0) == '1';
            }
        }

        int nx, ny;
        int answer = 0;
        while (true) {
            // 1
            if (!completed[x][y]) {
                completed[x][y] = true;
                ++answer;
            }

            // 3
            int i;
            for (i = 0; i < 4; ++i) {
                dir = (dir + 3) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if (graph[nx][ny] || completed[nx][ny])
                    continue;
                x = nx;
                y = ny;
                break;
            }

            // 2
            if (i == 4) {
                nx = x - dx[dir];
                ny = y - dy[dir];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (graph[nx][ny])
                    break;
                x = nx;
                y = ny;
            }
        }
        System.out.println(answer);
        br.close();
    }
}