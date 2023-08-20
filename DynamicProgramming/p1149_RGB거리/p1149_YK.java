package DynamicProgramming.p1149_RGB거리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1149_YK {

    static int N;
    static int[][] dp, input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                input[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = 1_000_001;
            }
        }
        // 해당 집을 어떤 색으로 칠했을 때 최소비용?
        int r1, r2, r3;
        r1 = dp(0, 0);
        r2 = dp(0, 1);
        r3 = dp(0, 2);

        r1 = Math.min(r1, r2);
        r1 = Math.min(r1, r3);

        bw.write(String.valueOf(r1));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dp(int x, int c) {
        if (x == N - 1)
            return dp[x][c] = input[x][c];

        if (dp[x][c] < 1_000_001)
            return dp[x][c];

        for (int i = 0; i < 3; i++) {
            if (i == c) continue;
            dp[x][c] = Math.min(dp[x][c], dp(x + 1, i));
        }

        return dp[x][c] += input[x][c];
    }
}
