package DynamicProgramming.p2302_극장좌석;

import java.io.*;

public class p2302_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] seats = new boolean[N + 1];
        seats[0] = true;
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; ++i) {
            seats[Integer.parseInt(br.readLine())] = true;
        }

        int[] dp = new int[N + 2];
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= N; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int cnt = 0, result = 1;
        for (int i = 1; i <= N; ++i) {
            if (!seats[i]) ++cnt;
            else {
                result *= dp[cnt];
                cnt = 0;
            }
        }

        result *= dp[cnt];
        System.out.println(result);
        br.close();
    }
}
