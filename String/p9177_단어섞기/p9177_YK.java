package String.p9177_단어섞기;

import java.io.*;
import java.util.*;

public class p9177_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        char[] a, b, c;
        for (int tc = 1; tc <= N; ++tc) {
            sb.append("Data set ").append(tc).append(": ");
            st = new StringTokenizer(br.readLine());
            a = st.nextToken().toCharArray();
            b = st.nextToken().toCharArray();
            c = st.nextToken().toCharArray();

            int aLen = a.length, bLen = b.length;
            // dp[i][j] : a를 i번째 글자, b를 j번째 글자까지 넣었을 때 c의 i+j번째 글자까지 커버가 가능한지
            boolean[][] dp = new boolean[aLen + 2][bLen + 2];
            dp[1][1] = true;
            for (int i = 1; i < aLen + 2; ++i) {
                for (int j = 1; j < bLen + 2; ++j) {
                    int x = i - 2, y = j - 2;
                    if (x >= 0 && dp[i - 1][j] && a[x] == c[x + y + 1]) dp[i][j] = true;
                    else if (y >= 0 && dp[i][j - 1] && b[y] == c[x + y + 1]) dp[i][j] = true;
                }
            }

            if (dp[aLen + 1][bLen + 1]) sb.append("yes");
            else sb.append("no");
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
