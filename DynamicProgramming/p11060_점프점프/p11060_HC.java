package DynamicProgramming.p11060_점프점프;

import java.io.*;
import java.util.*;

public class p11060_HC {

    private static final int INF = 1234567;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 1; j <= A[i]; ++j) {
                if (i + j < N)
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        System.out.println(dp[N - 1] == INF ? -1 : dp[N - 1]);
        br.close();
    }
}