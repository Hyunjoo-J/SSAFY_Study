package BinarySearch.p7453_합이0인네정수;

import java.io.*;
import java.util.*;

public class p7453_YK {

    static int[] A, B, C, D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                AB[i * N + j] = A[i] + B[j];
                CD[i * N + j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);
        int p1 = 0, p2 = N * N - 1;
        long result = 0, tmp;
        while (p1 < N * N & p2 >= 0) {
            tmp = AB[p1] + CD[p2];
            if (tmp == 0) {
                int t1 = 1, t2 = 1;
                while (p1 + t1 <= N * N - 1 && AB[p1] == AB[p1 + t1]) {
                    t1++;
                }
                while (p2 - t2 >= 0 && CD[p2] == CD[p2 - t2]) {
                    t2++;
                }
                result += (long) t1 * t2;
                p1 += t1;
                p2 -= t2;
            }
            else if (tmp > 0) {
                p2--;
            }
            else {
                p1++;
            }
        }

        System.out.println(result);
        br.close();
    }
}
