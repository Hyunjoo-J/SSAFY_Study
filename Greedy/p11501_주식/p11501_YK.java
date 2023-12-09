package Greedy.p11501_주식;

import java.io.*;
import java.util.*;

public class p11501_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] input = new int[1_000_001];

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            int max = input[N - 1];
            for (int i = N - 1; i > -1; --i) {
                if (max <= input[i]) {
                    max = input[i];
                    continue;
                }
                result += max - input[i];
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

}
