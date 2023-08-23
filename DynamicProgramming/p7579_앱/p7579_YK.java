package DynamicProgramming.p7579_앱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p7579_YK {
    static int N, M;
    static int[] mm, cost;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mm = new int[N + 1];
        cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            mm[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int costs = 0;
        for (int i = 1; i <= N; ++i) {
            cost[i] = Integer.parseInt(st.nextToken());
            costs += cost[i];
        }

        dp = new int[N + 1][costs + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= costs; ++j) {
                int m = mm[i];
                int c = cost[i];
                if (c <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c] + m);

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);

            }
        }

        for (int i = 1; i <= costs; i++) {
            if (dp[N][i] >= M) {
                bw.write(String.valueOf(i));
                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
