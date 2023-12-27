package Greedy.p2785_체인;

import java.io.*;
import java.util.*;

public class p2785_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] chains = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            chains[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chains);

        int result = 0;
        int p1 = 0, p2 = N - 1;
        while (p1 < p2) {
            while (p1 < p2 && chains[p1] > 0) {
                ++result;
                --chains[p1];
                --p2;
            }
            ++p1;
        }

        System.out.println(result);
        br.close();
    }
}
