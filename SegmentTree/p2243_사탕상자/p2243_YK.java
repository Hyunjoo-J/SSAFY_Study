package SegmentTree.p2243_사탕상자;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p2243_YK {
    static int[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        S = 1 << 20;
        tree = new int[S << 1];
        int candy;

        int a, b, c;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                candy = get(1, 0, S - 1, b);
                bw.write(String.valueOf(candy));
                bw.newLine();
                update(candy, -1);
            } else {
                c = Integer.parseInt(st.nextToken());
                update(b, c);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int target, int diff) {
        int node = S + target;
        while(node >= 1) {
            tree[node] += diff;
            node >>= 1;
        }
    }

    private static int get(int node, int left, int right, int target) {
        if (left == right) return left;

        int mid = (left + right) >> 1;

        if (tree[node << 1] >= target) {
            return get(node << 1, left, mid, target);
        }

        return get((node << 1) + 1, mid + 1, right, target - tree[node << 1]);
    }
}
