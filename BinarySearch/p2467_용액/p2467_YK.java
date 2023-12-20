package BinarySearch.p2467_용액;

import java.io.*;
import java.util.*;

public class p2467_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] liquids = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0, p2 = N - 1;
        int num1 = -1, num2 = -1;
        int min = Math.abs(liquids[p1] + liquids[p2]);

        while (p1 < p2 && min != 0) {
            int tmp = liquids[p1] + liquids[p2];

            if (min >= Math.abs(tmp)) {
                min = Math.abs(tmp);
                num1 = liquids[p1];
                num2 = liquids[p2];
            }

            if (tmp < 0) {
                ++p1;
            } else {
                --p2;
            }
        }

        System.out.println(num1 + " " + num2);
        br.close();
    }
}
