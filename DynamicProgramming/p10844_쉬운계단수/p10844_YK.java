package DynamicProgramming.p10844_쉬운계단수;

import java.io.*;

public class p10844_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int LIMIT = 1_000_000_000;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        dp[1] = new long[] {0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i = 2; i <= N; ++i) {
            long[] pre = dp[i - 1];
            dp[i][0] = pre[1] % LIMIT;
            dp[i][9] = pre[8] % LIMIT;
            for (int j = 1; j < 9; ++j) {
                dp[i][j] = (pre[j - 1] + pre[j + 1]) % LIMIT;
            }
        }

        int result = 0;
        for (long i : dp[N]) {
            result += i;
            result %= LIMIT;
        }
        System.out.println(result);
        br.close();
    }
}