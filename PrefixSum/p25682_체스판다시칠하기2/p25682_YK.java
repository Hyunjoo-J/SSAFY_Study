package PrefixSum.p25682_체스판다시칠하기2;

import java.io.*;
import java.util.*;

public class p25682_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] w_start = new int[N + 1][M + 1];
        int[][] b_start = new int[N + 1][M + 1];
        String line;
        boolean now = true;
        char tmp;
        for (int i = 1; i <= N; ++i) {
            line = br.readLine();
            if (i > 1 && M % 2 == 0) now = !now;
            for (int j = 1; j <= M; ++j) {
                tmp = line.charAt(j - 1);
                w_start[i][j] = w_start[i][j - 1] + w_start[i - 1][j] - w_start[i - 1][j - 1] + check(now, tmp);
                b_start[i][j] = b_start[i][j - 1] + b_start[i - 1][j] - b_start[i - 1][j - 1] + check(!now, tmp);
                now = !now;
            }
        }

        int result = Integer.MAX_VALUE;
        int tmp1, tmp2; K--;
        for (int i = 1; i <= N - K; ++i) {
            for (int j = 1; j <= M - K; ++j) {
                tmp1 = w_start[i + K][j + K] - w_start[i - 1][j + K]
                        - w_start[i + K][j - 1] + w_start[i - 1][j - 1];
                tmp2 = b_start[i + K][j + K] - b_start[i - 1][j + K]
                        - b_start[i + K][j - 1] + b_start[i - 1][j - 1];
                result = Math.min(Math.min(tmp1, tmp2), result);
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int check(boolean now, char tmp) {
        char ch = now ? 'W' : 'B';
        return tmp == ch ? 0 : 1;
    }
}
