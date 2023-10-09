package BinarySearch.p2473_세용액;

import java.util.*;
import java.io.*;

public class p2473_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] input = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        int p1, p2;
        long[] result = new long[3];
        long min = Long.MAX_VALUE;
        long tmp;
        for (int i = 0; i < N; ++i) {
            p1 = 0; p2 = N - 1;
            while (p1 < p2) {
                if (i == p1) {
                    p1++;
                    continue;
                }
                if (i == p2) {
                    p2--;
                    continue;
                }

                tmp = input[i] + input[p1] + input[p2];
                if (Math.abs(tmp) < min) {
                    min = Math.abs(tmp);
                    result[0] = input[i];
                    result[1] = input[p1];
                    result[2] = input[p2];
                    if (min == 0) break;
                }

                if (tmp < 0) {
                    p1++;
                } else {
                    p2--;
                }
            }
            if (min == 0) break;
        }

        Arrays.sort(result);
        bw.write(result[0] + " " + result[1] + " " + result[2]);
        bw.flush();
        bw.close();
        br.close();
    }
}
