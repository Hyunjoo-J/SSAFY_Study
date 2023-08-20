package DynamicProgramming.p2579_계단오르기;

import java.io.*;

public class p2579_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N + 1];
        for (int i = 1; i <= N; ++i)
            A[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[2][N + 1];

        dp[0][1] = A[1];
        for (int i = 2; i <= N; ++i) {
            dp[0][i] = A[i] + Math.max(dp[0][i - 2], dp[1][i - 2]);
            dp[1][i] = A[i] + dp[0][i - 1];
        }
        System.out.println(Math.max(dp[0][N], dp[1][N]));
        br.close();
    }
}
