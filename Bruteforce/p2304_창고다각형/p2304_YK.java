package Bruteforce.p2304_창고다각형;

import java.io.*;
import java.util.*;

public class p2304_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int s = 1001, e = 0;
        int[] space = new int[1001];
        int max = 0, mx = 0;

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            space[L] = H;
            if (L < s) s = L;
            if (L > e) e = L;
            if (H > max) {
                max = H;
                mx = L;
            }
        }

        int height = 0;
        int result = -max;
        for (int i = s; i <= mx; ++i) {
            if (height < space[i]) {
                height = space[i];
            }
            result += height;
        }

        height = 0;
        for (int i = e; i >= mx; --i) {
            if (height < space[i]) {
                height = space[i];
            }
            result += height;
        }

        System.out.println(result);
        br.close();
    }
}
