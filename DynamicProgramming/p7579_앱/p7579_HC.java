package DynamicProgramming.p7579_앱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p7579_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        int[] m = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][10001];
        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= 10000; ++j) {
                if (j >= m[i])
                    dp[i][j] = Math.max(dp[i - 1][j], A[i] + dp[i - 1][j - m[i]]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        int answer = 0;
        for (int i = 0; i <= 10000; ++i) {
            if (dp[N][i] >= M) {
                answer = i;
                break;
            }
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }
}
