package String.p9177_단어섞기;

import java.io.*;
import java.util.*;

public class p9177_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            String word1 = st.nextToken();
            String word2 = st.nextToken();
            String word3 = st.nextToken();
            sb.append("Data set ").append(tc).append(": ")
                    .append((check(word1, word2, word3) ? "yes\n" : "no\n"));
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static boolean check(String word1, String word2, String word3) {
        int[] counter = new int[200];
        int n = word1.length();
        int m = word2.length();
        int l = n + m;

        for (int i = 0; i < n; ++i)
            ++counter[word1.charAt(i)];
        for (int i = 0; i < m; ++i)
            ++counter[word2.charAt(i)];
        for (int i = 0; i < l; ++i)
            --counter[word3.charAt(i)];
        for (int i = 0; i < 200; ++i) {
            if (counter[i] != 0)
                return false;
        }

        int idx = 0;
        for (int i = 0; i < l && idx < n; ++i) {
            if (word3.charAt(i) == word1.charAt(idx))
                ++idx;
        }
        if (idx < n)
            return false;

        idx = 0;
        for (int i = 0; i < l && idx < m; ++i) {
            if (word3.charAt(i) == word2.charAt(idx))
                ++idx;
        }
        if (idx < m)
            return false;

        return true;
    }
}