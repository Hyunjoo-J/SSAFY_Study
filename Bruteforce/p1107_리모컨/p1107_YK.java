package Bruteforce.p1107_리모컨;

import java.io.*;
import java.util.*;

public class p1107_YK {
    static Integer[] buttons = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static int N;
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());
        if (M > 0) st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            buttons[Integer.parseInt(st.nextToken())] = null;
        }

        // 숫자 버튼을 안 누르고 차이 값
        result = Math.abs(N - 100);

        // 버튼을 누른다.
        dfs(0, 0);

        System.out.println(result);
        br.close();
    }

    private static void dfs(int k, int cnt) {
        if (cnt >= result) return;
        if (k >= 1_000_000) return;

        if (cnt != 0) result = Math.min(Math.abs(N - k) + cnt, result);
        for (int i = 0; i < 10; ++i) {
            if (buttons[i] == null) continue;
            if (k == 0 && i == 0 && cnt != 0) continue;
            dfs(k * 10 + i, cnt + 1);
        }
    }
}
