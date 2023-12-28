package UnionFind.p20040_사이클게임;

import java.io.*;
import java.util.*;

public class p20040_YK {

    static int[] parents;
    static int N;
    static void init() {
        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        int M = Integer.parseInt(st.nextToken());

        init();
        int result = 0;
        for (int i = 1; i <= M; ++i) {
            st = new StringTokenizer(br.readLine());
            if (result != 0) continue;
            if (!union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                result = i;
            }
        }

        System.out.println(result);
        br.close();
    }
}
