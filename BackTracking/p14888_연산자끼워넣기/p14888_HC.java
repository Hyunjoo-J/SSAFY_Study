package BackTracking.p14888_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class p14888_HC {

    private static int N;
    private static int[] A, S;
    private static int[] answer = {Integer.MIN_VALUE, Integer.MAX_VALUE};

    private static void dfs(int idx, int num) {
        if (idx == N) {
            answer[0] = Math.max(answer[0], num);
            answer[1] = Math.min(answer[1], num);
            return;
        }
        for (int i = 0; i < 4; ++i) {
            if (S[i] < 1)
                continue;

            --S[i];
            switch (i) {
                case 0:
                    dfs(idx + 1, num + A[idx]);
                    break;
                case 1:
                    dfs(idx + 1, num - A[idx]);
                    break;
                case 2:
                    dfs(idx + 1, num * A[idx]);
                    break;
                case 3:
                    dfs(idx + 1, num / A[idx]);
                    break;
            }
            ++S[i];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        S = new int[4];

        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, A[0]);

        bw.write(String.valueOf(answer[0]));
        bw.newLine();
        bw.write(String.valueOf(answer[1]));
        bw.flush();
        bw.close();
        br.close();
    }

}
