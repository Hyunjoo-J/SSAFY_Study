package Bruteforce.p16945_매직스퀘어로변경하기;

import java.io.*;
import java.util.*;

public class p16945_HC {

    private static final int[][] indexer = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] perm = new int[9];
        int[] arr = new int[9];
        for (int i = 0, index = 0; i < 3; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j, ++index) {
                arr[index] = Integer.parseInt(st.nextToken());
                perm[index] = index + 1;
            }
        }

        int answer = Integer.MAX_VALUE;
        do {
            if (isMagicSquare(perm)) {
                answer = Math.min(answer, calculateCost(arr, perm));
            }
        } while (np(perm, 9));
        System.out.println(answer);
        br.close();
    }

    private static int calculateCost(int[] arr, int[] magicSquare) {
        int cost = 0;
        for (int i = 0; i < 9; ++i) {
            cost += Math.abs(arr[i] - magicSquare[i]);
        }
        return cost;
    }

    private static boolean isMagicSquare(int[] arr) {
        for (int[] indices: indexer) {
            int sum = 0;
            for (int index: indices) {
                sum += arr[index];
            }
            if (sum != 15)
                return false;
        }
        return true;
    }

    private static boolean np(int[] perm, int n) {
        int i = n - 1;
        while (i > 0 && perm[i - 1] > perm[i])
            --i;

        if (i == 0)
            return false;

        int j = n - 1;
        while (perm[i - 1] > perm[j])
            --j;
        swap(perm, i - 1, j);

        int k = n - 1;
        while (i < k)
            swap(perm, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
