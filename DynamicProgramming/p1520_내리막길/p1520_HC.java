package DynamicProgramming.p1520_내리막길;

import java.io.*;
import java.util.*;

public class p1520_HC {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int M, N;
    private static int[][] graph;
    private static int[][] mem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[M][N];
        mem = new int[M][N];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(mem[i], -1);
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mem[M - 1][N - 1] = 1;
        dfs(0, 0);
        bw.write("" + mem[0][0]);
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int x, int y) {
        if (mem[x][y] != -1) {
            return mem[x][y];
        }
        int nx, ny, temp = 0;
        mem[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                continue;
            if (graph[nx][ny] >= graph[x][y])
                continue;
            temp += dfs(nx, ny);
        }
        return mem[x][y] = temp;
    }
}