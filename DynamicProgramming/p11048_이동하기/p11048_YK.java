package DynamicProgramming.p11048_이동하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p11048_YK {
    static int N, M;
    static int[][] map, dp;
    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 사탕은 개수는 0부터...
                dp[i][j] = -1;
            }
        }

        bw.write(String.valueOf(dfs(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1)
            return dp[x][y] = map[x][y];

        if (dp[x][y] > -1)
            return dp[x][y];

        for (int i = 0; i < 3; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny));
        }

        return dp[x][y] += map[x][y];
    }
}
