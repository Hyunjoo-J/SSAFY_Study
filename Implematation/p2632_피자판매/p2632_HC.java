package Implematation.p2632_피자판매;

import java.io.*;
import java.util.*;

public class p2632_HC {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int buy = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int aSize = 0;
        int bSize = 0;

        int[] A = new int[(m << 1) + 1];
        int[] B = new int[(n << 1) + 1];

        for (int i = 1; i <= m; ++i) {
            A[i] = Integer.parseInt(br.readLine());
            A[i + m] = A[i];
            aSize += A[i];
        }
        for (int i = 1; i <= n; ++i) {
            B[i] = Integer.parseInt(br.readLine());
            B[i + n] = B[i];
            bSize += B[i];
        }

        accumulate(A);
        accumulate(B);

        int[] counter = new int[2000020];
        ++counter[0];
        ++counter[bSize];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                ++counter[B[i + j] - B[i - 1]];
            }
        }

        int answer = counter[buy];
        if (buy >= aSize) {
            answer += counter[buy - aSize];
        }
        for (int i = 1, size; i <= m; ++i) {
            for (int j = 0; j < m - 1; ++j) {
                size = A[i + j] - A[i - 1];
                if (buy < size)
                    break;
                answer += counter[buy - size];
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static void accumulate(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            arr[i] += arr[i - 1];
        }
    }
}
