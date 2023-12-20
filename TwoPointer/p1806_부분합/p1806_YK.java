package TwoPointer.p1806_부분합;

import java.io.*;
import java.util.*;

public class p1806_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int len = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0;
        long sum = nums[0];
        while (p1 <= p2) {
            if (sum < S) {
                if (p2 == N - 1) break;
                sum += nums[++p2];
            } else {
                if (len > p2 - p1) {
                    len = p2 - p1 + 1;
                }
                sum -= nums[p1++];
            }
        }

        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
        br.close();
    }
}
