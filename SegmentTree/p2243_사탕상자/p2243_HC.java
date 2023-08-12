package SegmentTree.p2243_사탕상자;

import java.io.*;
import java.util.*;

public class p2243_HC {

    private static final int S = 1048576;
    private static int[] tree = new int[2097152];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, A, B, C, x;

        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (A == 1) {
                x = query(1, 0, S - 1, B);
                sb.append(x).append("\n");
                update(1, 0, S - 1, x, -1);
            } else {
                C = Integer.parseInt(st.nextToken());
                update(1, 0, S - 1, B, C);
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int query(int node, int left, int right, int rank) {
        if (left == right) {
            return left;
        } else {
            int mid = (left + right) >> 1;
            if (tree[node << 1] >= rank) {
                return query(node << 1, left, mid, rank);
            } else {
                return query((node << 1) + 1, mid + 1, right, rank - tree[node << 1]);
            }
        }
    }

    private static void update(int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) >> 1;
            update(node << 1, left, mid, target, diff);
            update((node << 1) + 1, mid + 1, right, target, diff);
        }
    }
}