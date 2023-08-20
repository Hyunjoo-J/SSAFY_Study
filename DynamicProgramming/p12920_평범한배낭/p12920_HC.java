package DynamicProgramming.p12920_평범한배낭;

import java.io.*;
import java.util.*;

public class p12920_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> wgh = new ArrayList<>();
        List<Integer> val = new ArrayList<>();

        int V, C, K, min;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            for (int j = 1; K > 0; j <<= 1) {
                min = Math.min(j, K);
                wgh.add(V * min);
                val.add(C * min);
                K -= min;
            }
        }

        int size = wgh.size();
        int[][] dp = new int[size + 1][M + 1];
        for (int i = 1; i <= size; ++i) {
            for (int j = 0; j <= M; ++j) {
                if (j - wgh.get(i - 1) >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], val.get(i - 1) + dp[i - 1][j - wgh.get(i - 1)]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        bw.write("" + dp[size][M]);
        bw.flush();
        bw.close();
        br.close();
    }
}
