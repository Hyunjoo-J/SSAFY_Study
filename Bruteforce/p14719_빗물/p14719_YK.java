package Bruteforce.p14719_빗물;

import java.io.*;
import java.util.*;
public class p14719_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; ++i) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftMax = new int[W];
        int[] rightMax = new int[W];

        leftMax[0] = heights[0];
        rightMax[W - 1] = heights[W - 1];
        for (int i = 1, lmax = heights[0], rmax = heights[W - 1]; i < W; ++i) {
            lmax = Math.max(lmax, heights[i]);
            rmax = Math.max(rmax, heights[W - 1 - i]);
            leftMax[i] = lmax;
            rightMax[W - 1 - i] = rmax;
        }

        int result = 0;
        for (int i = 0; i < W; ++i) {
            result += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }

        System.out.println(result);
        br.close();
    }
}
