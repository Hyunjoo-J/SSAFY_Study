package Bruteforce.p20529_가장가까운세사람의심리적거리;

import java.io.*;
import java.util.*;

public class p20529_YK {

    static int N, result;
    static String[] mbti;
    static int[] chosen;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            result = Integer.MAX_VALUE;
            mbti = new String[N];
            chosen = new int[3];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                mbti[i] = st.nextToken();
            }

            combi(0, 0, 0); // 조합...
            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void combi(int start, int cnt, int dist) {
        if (dist >= result) return;
        if (cnt == 3) {
            result = dist;
            return;
        }

        for (int i = start; i < N; ++i) {
            chosen[cnt] = i;
            combi(i + 1, cnt + 1, dist + calculate(cnt));
        }
    }

    private static int calculate(int cnt) {
        if (cnt == 0) return 0; // 첫 번째 숫자
        if (cnt == 1) { // 두 번째 숫자 (첫 번째 숫자와 비교)
            return getDist(chosen[0], chosen[1]);
        }
        if (cnt == 2) { // 세 번째 숫자 (첫 번째, 두 번째 숫자와 비교)
            return getDist(chosen[0], chosen[2]) + getDist(chosen[1], chosen[2]);
        }
        return -1;
    }

    private static int getDist(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 4; ++i) { // 심리 거리 구하기
            if (mbti[x].charAt(i) != mbti[y].charAt(i)) ++ans;
        }
        return ans;
    }

}
