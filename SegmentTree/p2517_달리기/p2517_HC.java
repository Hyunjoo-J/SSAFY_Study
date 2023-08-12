package SegmentTree.p2517_달리기;

import java.io.*;
import java.util.*;

public class p2517_HC {

    private static int N, S;
    private static int[] A;
    private static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> keySet = new HashSet<>(500009);
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(br.readLine());
            keySet.add(A[i]);
        }

        S = 1;
        while (S < N) {
            S <<= 1;
        }
        tree = new int[S * 2];

        coordCompression(A, keySet);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            sb.append(query(1, 0, S - 1, A[i] + 1, N - 1) + 1)
                    .append("\n");
            update(1, 0, S - 1, A[i], 1);
        }
        System.out.println(sb.toString());
        br.close();
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

    private static int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return query(node << 1, left, mid, queryLeft, queryRight)
                + query((node << 1) + 1, mid + 1, right, queryLeft, queryRight);
    }

    private static void coordCompression(int[] A, Set<Integer> keySet) {
        List<Integer> keys = new ArrayList<>(keySet);
        Map<Integer, Integer> map = new HashMap<>(N + 7);
        keys.sort(Comparator.comparingInt(o -> o));
        int cIdx = 0;
        for (int key: keys) {
            map.put(key, cIdx++);
        }

        for (int i = 0; i < N; ++i) {
            A[i] = map.get(A[i]);
        }
    }
}