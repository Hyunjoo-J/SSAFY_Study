package Bruteforce.p18429_근손실;

import java.io.*;
import java.util.*;

public class p18429_YK {

    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] kit = new int[N];
        int[] order = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            kit[i] = Integer.parseInt(st.nextToken());
            order[i] = i;
        }

        int result = 0;
        do {
            if (check(order,kit)) {
                ++result;
            }
        } while (np(order));

        System.out.println(result);
        br.close();
    }

    private static boolean np(int[] order) {
        int i = N - 1;
        while (i > 0 && order[i - 1] > order[i]) --i;
        if (i == 0) return false;

        int j = N - 1;
        while (order[i - 1] > order[j]) --j;
        swap(i - 1, j, order);

        int k = N - 1;
        while (i < k) swap(i++, k--, order);

        return true;
    }

    private static void swap(int i, int j, int[] order) {
        int tmp = order[i];
        order[i] = order[j];
        order[j] = tmp;
    }

    private static boolean check(int[] order, int[] kit) {
        int muscle = 500;
        for (int i = 0; i < N; ++i) {
            muscle += kit[order[i]] - K;
            if (muscle < 500) return false;
        }
        return true;
    }
}
