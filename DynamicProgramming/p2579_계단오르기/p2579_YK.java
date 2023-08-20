package DynamicProgramming.p2579_계단오르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p2579_YK {

    static int N;
    static int[] input, dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        dp = new int[N][2];
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(br.readLine());
        }

        go(0, 0);
        if (N > 1) go(1, 0);
        int r1 = 0, r2 = 0;
        r1 = Math.max(dp[0][0], dp[0][1]);
        if (N > 1) r2 = Math.max(dp[1][0], dp[1][1]);
        bw.write(String.valueOf(Math.max(r1, r2)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int go(int x, int conti) {
        if (x == N - 1)
            return dp[x][conti] = input[x];

        if (dp[x][conti] != 0)
            return dp[x][conti];

        if (conti < 1) { // 이번 칸이 첫 칸이라 다음 칸을 갈 수 있다
            dp[x][conti] = go(x + 1, conti + 1);
        }

        if (x + 2 < N) { // 마지막 칸에 갈 수 있다.
            dp[x][conti] = Math.max(dp[x][conti], go(x + 2, 0));
        }
        else if (dp[x][conti] == 0){ // 못간다.
            return dp[x][conti] = Integer.MIN_VALUE;
        }

        return dp[x][conti] += input[x];
    }
}

