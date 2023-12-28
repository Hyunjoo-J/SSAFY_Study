package DynamicProgramming.p11066_파일합치기;

import java.io.*;
import java.util.*;

public class p11066_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] nums = new int[500];
        int[] sum = new int[500];
        int[][] dp = new int[500][500];

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            nums[0] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < N; ++i) {
                nums[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + nums[i];
            }

//            for (int i = 0; i < N; ++i) {
//                Arrays.fill(dp[i], Integer.MAX_VALUE >> 1);
//            }

            for (int k = 1; k < N; ++k) { // 몇 개 간격으로 합칠지
                for (int i = 0; i < N - k; ++i) {
                    int j = i + k;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int m = i; m < j; ++m) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m + 1][j] + sum[j] - sum[i] + nums[i]);
                    }
                }
            }

            sb.append(dp[0][N - 1]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
