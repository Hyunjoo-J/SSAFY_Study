package SegmentTree.p2517_달리기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class p2517_YK {

    static int N, S;
    static int[] input, tree, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        result = new int[N];
        Set<Integer> hashSet = new HashSet<>(500000);
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(br.readLine());
            hashSet.add(input[i]);
        }

        // 좌표 압축!
        compress(hashSet);

        // 세그먼트 트리 초기화
        S = 1;
        while (S < N) S <<= 1;
        tree = new int[S << 1];

        for (int i = 0; i < N; ++i) {
            update(input[i]);
            bw.write(String.valueOf(get(1, 0, S - 1, input[i] + 1, N - 1) + 1));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int get(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) >> 1;
        return get(node << 1, start, mid, left, right)
                + get((node << 1) + 1, mid + 1, end, left, right);
    }

    private static void update(int k) {
        tree[S + k] = 1;

        int node = (S + k) >> 1;
        while (node >= 1) {
            ++tree[node];
            node >>= 1;
        }
    }

    private static void compress(Set<Integer> hashSet) {
        List<Integer> keys = new ArrayList<>(hashSet);
        Map<Integer, Integer> hashMap = new HashMap<>(N);
        keys.sort(Comparator.comparingInt(o -> o));

        int idx = 0;
        for (int key : keys) {
            hashMap.put(key, idx++);
        }
        for (int i = 0; i < N; ++i) {
            input[i] = hashMap.get(input[i]);
        }
    }
}