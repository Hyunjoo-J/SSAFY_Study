package Greedy.p28353_고양이카페;

import java.io.*;
import java.util.*;

public class p28353_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int result = 0;
        int i = 0, j = N - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum <= W) {
                ++result;
                ++i;
            }
            --j;
        }

        System.out.println(result);
        br.close();
    }
}
