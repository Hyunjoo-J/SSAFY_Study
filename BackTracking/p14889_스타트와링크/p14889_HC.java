package BackTracking.p14889_스타트와링크;

import java.io.*;
import java.util.*;

public class p14889_HC {

    private static int N, N2, answer = Integer.MAX_VALUE;
    private static int[][] S;
    private static boolean[] used;

    private static void findAnswer() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N; ++i) {
            if (used[i]) {
                for (int j = i + 1; j < N; ++j) {
                    if (used[j]) {
                        start += S[i][j];
                        start += S[j][i];
                    }
                }
            } else {
                for (int j = i + 1; j < N; ++j) {
                    if (!used[j]) {
                        link += S[i][j];
                        link += S[j][i];
                    }
                }
            }
        }
        answer = Math.min(answer, Math.abs(start - link));
    }

    private static void dfs(int idx, int depth) {
        if (depth == N2) {
            findAnswer();
            return;
        }
        for (int i = idx; i < N; ++i) {
            used[i] = true;
            dfs(i + 1, depth + 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        N2 = N >> 1;
        S = new int[N][N];
        used = new boolean[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        bw.write("" + answer);

        bw.flush();
        bw.close();
        br.close();
    }

}
