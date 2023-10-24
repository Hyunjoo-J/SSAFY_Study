package DynamicProgramming.p15486_퇴사2;

import java.io.*;
import java.util.*;

public class p15486_YK {
    static int N;
    static Work[] works;
    static int[] dp;

    static class Work {
        int time, profit;

        public Work(int time, int profit) {
            this.time = time;
            this.profit = profit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        works = new Work[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            works[i] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[N + 1];

        for (int i = 0; i < N; ++i) {
            int t = works[i].time;
            int p = works[i].profit;
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
            if (i + t <= N) dp[i + t] = Math.max(dp[i] + p, dp[i + t]);
        }

        System.out.println(dp[N]);
        br.close();
    }
}
