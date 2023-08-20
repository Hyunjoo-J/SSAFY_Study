package DynamicProgramming.p11060_점프점프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p11060_YK {
    static int N;
    static int[] map, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            map[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE - 1;
        }

        bw.write(String.valueOf(dfs(0) == Integer.MAX_VALUE - 1? -1 : dfs(0)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int x) {
        if (x == N - 1) return dp[x] = 0;
        if (dp[x] != Integer.MAX_VALUE - 1) return dp[x];

        for (int i = 1; i <= map[x]; ++i) {
            if (x + i >= N) continue;
            dp[x] = Math.min(dp[x], dfs(x + i) + 1);
        }

        return dp[x];
    }
}
