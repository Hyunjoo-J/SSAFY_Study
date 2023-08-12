package SegmentTree.p2357_최소값과최댓값;

import java.io.*;
import java.util.*;

public class p2357_HC {

    private static int N, S;
    private static int[] minTree;
    private static int[] maxTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N)
            S <<= 1;
        minTree = new int[S << 1];
        maxTree = new int[S << 1];

        for (int i = 0; i < N; ++i) {
            minTree[S + i] = Integer.parseInt(br.readLine());
            maxTree[S + i] = minTree[S + i];
        }

        for (int i = S - 1; i > 0; --i) {
            int nn = i << 1;
            minTree[i] = Math.min(minTree[nn], minTree[nn + 1]);
            maxTree[i] = Math.max(maxTree[nn], maxTree[nn + 1]);
        }

        int a, b;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(minQuery(1, 0, S - 1, a - 1, b - 1) + " ");
            bw.write(maxQuery(1, 0, S - 1, a - 1, b - 1) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int minQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Integer.MAX_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return minTree[node];
        }
        int mid = (left + right) >> 1;
        return Math.min(minQuery(node << 1, left, mid, queryLeft, queryRight),
                minQuery((node << 1) + 1, mid + 1, right, queryLeft, queryRight));
    }

    private static int maxQuery(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return Integer.MIN_VALUE;
        }
        if (queryLeft <= left && right <= queryRight) {
            return maxTree[node];
        }
        int mid = (left + right) >> 1;
        return Math.max(maxQuery(node << 1, left, mid, queryLeft, queryRight),
                maxQuery((node << 1) + 1, mid + 1, right, queryLeft, queryRight));
    }
}