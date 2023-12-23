package UnionFind.p20040_사이클게임;

import java.io.*;
import java.util.*;

public class p20040_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n];
        for (int i = 0; i < n; ++i)
            parent[i] = i;

        boolean cycle = false;
        int answer = -1;
        for (int i = 1, a, b; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            if (cycle)
                continue;

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (find(parent, a) == find(parent, b)) {
                cycle = true;
                answer = i;
            }
            union(parent, a, b);
        }
        System.out.println(cycle ? answer : 0);
        br.close();
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            return find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int a, int b){
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}
