package TwoPointer.p1806_부분합;

import java.io.*;
import java.util.*;

public class p27172_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] players = new int[N];
        boolean[] isThere = new boolean[1_000_001];
        int[] score = new int[1_000_001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            players[i] = Integer.parseInt(st.nextToken());
            isThere[players[i]] = true;
        }

        // 1은 모든 수의 약수이며, 다른 수로 나누어 떨어지지 않는다
        if (isThere[1]) {
            Arrays.fill(score, -1);
            score[1] = N - 1;
        }

        for (int p : players) {
            if (p == 1) continue;

            for (int i = 2; i <= Math.sqrt(p); ++i) {
                if (p % i != 0) continue;

                // 1과 p를 제외한 약수만 진입 가능
                if (isThere[i]) {
                    ++score[i];
                    --score[p];
                }

                // 제곱 수의 중복 연산 막기 (ex. 4^2)
                if (isThere[p / i] && i != Math.sqrt(p)) {
                    ++score[p / i];
                    --score[p];
                }
            }
        }

        for (int p : players) {
            sb.append(score[p]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
