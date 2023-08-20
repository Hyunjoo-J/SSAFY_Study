package DynamicProgramming.p11048_이동하기;

import java.io.*;
import java.util.*;

public class p11048_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; ++j) {
                dp[i][j] = Integer.parseInt(st.nextToken()) +
                        Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        System.out.println(dp[N][M]);
    }
}
