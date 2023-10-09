package TwoPointer.p22857_가장긴짝수연속한부분수열small;

import java.io.*;
import java.util.*;

public class p22857_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0;
        int odd = input[0] % 2 == 0 ? 0 : 1;
        int result = 1 - odd;

        for (int p2 = 1; p2 < N; ++p2) {
            if (input[p2] % 2 == 1) ++odd;
            while (odd > K) {
                if (input[p1++] % 2 == 1) --odd;
            }
            result = Math.max(result, p2 - p1 + 1 - odd);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
