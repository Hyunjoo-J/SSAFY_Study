package Greedy.p20300_서강근육맨;

import java.io.*;
import java.util.*;

public class p20300_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] list = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            list[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(list);
        long result = 0;
        if (N % 2 == 0) {
            for (int i = 0; i < N / 2; ++i) {
                result = Math.max(result, list[i] + list[N - 1 - i]);
            }
        } else {
            for (int i = 0; i < N / 2; ++i) {
                result = Math.max(result, list[i] + list[N - 2 - i]);
            }
            result = Math.max(result, list[N - 1]);
        }

        System.out.println(result);
        br.close();
    }
}
