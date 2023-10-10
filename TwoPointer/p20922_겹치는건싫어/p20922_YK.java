package TwoPointer.p20922_겹치는건싫어;

import java.io.*;
import java.util.*;
public class p20922_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] input = new int[N];
        int[] included = new int[200_001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int p1 = 0, p2 = 0;
        int result = 1;
        ++included[input[p1]];
        while (p2 < N - 1) {
            ++included[input[++p2]];
            while (p1 <= p2 && included[input[p2]] > K) {
                --included[input[p1++]];
            }
            result = Math.max(result, p2 - p1 + 1);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
