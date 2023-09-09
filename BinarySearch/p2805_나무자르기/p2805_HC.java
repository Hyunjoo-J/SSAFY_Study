package BinarySearch.p2805_나무자르기;

import java.io.*;
import java.util.*;

public class p2805_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0;
        long right = 2_000_000_000;
        long mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (simul(A, mid) >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left - 1);
        br.close();
    }

    private static int simul(int[] A, long h) {
        long res = 0;
        for (int num: A) {
            res += Math.max(0, num - h);
        }
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }
}
