package BinarySearch.p10816_숫자카드2;

import java.io.*;
import java.util.*;

public class p10816_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        final int MAX = 10_000_000;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[(MAX << 2) + 1];
        for (int i = 0, k; i < N; ++i) {
            k = Integer.parseInt(st.nextToken()) + MAX;
            ++nums[k];
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0, k; i < M; ++i) {
            k = Integer.parseInt(st.nextToken()) + MAX;
            sb.append(nums[k]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
