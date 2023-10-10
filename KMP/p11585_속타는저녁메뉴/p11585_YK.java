package KMP.p11585_속타는저녁메뉴;

import java.io.*;
import java.util.*;

public class p11585_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        char[] pattern = new char[N];
        char[] string = new char[N * 2 - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            pattern[i] = st.nextToken().charAt(0);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            string[i] = st.nextToken().charAt(0);
        }
        for (int i = N; i < N * 2 - 1; i++) {
            string[i] = string[i - N];
        }

        int[] p = new int[N];
        for (int i = 1, j = 0; i < N; ++i) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = p[j - 1];
            }

            if (pattern[i] == pattern[j]) p[i] = ++j;
        }

        int result = 0;
        for(int i = 0, j = 0, len = N * 2 - 1; i < len; ++i) {
            while (j > 0 && string[i] != pattern[j]) j = p[j - 1];

            if (string[i] == pattern[j]) {
                if (j == N - 1) {
                    result++;
                    j = p[j];
                } else {
                    j++;
                }
            }
        }

        int r1 = 0, r2 = N;
        for (int i = 1; i <= result; ++i) {
            if (N % i != 0 || result % i != 0) continue;
            r1 = result / i;
            r2 = N / i;
        }

        bw.write(r1 + "/" + r2);
        bw.flush();
        bw.close();
        br.close();
    }
}
