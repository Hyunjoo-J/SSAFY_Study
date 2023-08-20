package DynamicProgramming.p1520_내리막길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1520_YK {
    static int M, N;
    static int[][] input, dp;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        input = new int[M][N];
        dp = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                input[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        visited[0][0] = true;
        bw.write(String.valueOf(dp(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dp(int x, int y) {
        if (x == M - 1 && y == N - 1)
            return dp[x][y] = 1;

        if (dp[x][y] > -1)
            return dp[x][y];

        int nx, ny;
        dp[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;
            if (input[x][y] <= input[nx][ny]) continue;
            visited[nx][ny] = true;
            dp[x][y] += dp(nx, ny);
            visited[nx][ny] = false;
        }

        return dp[x][y];
    }
}
