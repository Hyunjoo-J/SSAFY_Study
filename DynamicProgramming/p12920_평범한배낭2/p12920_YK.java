package DynamicProgramming.p12920_평범한배낭2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p12920_YK {

    static int N, M;
    static ArrayList<Integer> value, weight;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        value = new ArrayList<>();
        weight = new ArrayList<>();

        int cnt = 0;
        int w, v, k, tmp;
        value.add(0);
        weight.add(0);
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            tmp = 1;
            while (k >= tmp) {
                cnt += 1;
                value.add(v * tmp);
                weight.add(w * tmp);
                k -= tmp;
                tmp <<= 1;
            }

            if (k != 0) {
                cnt += 1;
                value.add(v * k);
                weight.add(w * k);
            }
        }

        dp = new int[cnt + 1][M + 1];

        for (int i = 1; i <= cnt; ++i) {
            for (int j = 1; j <= M; ++j) {
                v = value.get(i);
                w = weight.get(i);

                if (j >= w) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                }
                else dp[i][j] = dp[i - 1][j];
            }
        }

        bw.write(String.valueOf(dp[cnt][M]));
        bw.flush();
        br.close();
        bw.close();
    }
}