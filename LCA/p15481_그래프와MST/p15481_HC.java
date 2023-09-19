package LCA.p15481_그래프와MST;

import java.io.*;
import java.util.*;

public class p15481_HC {

    private static class Node {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    private static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static int N;
    private static int[] depth;
    private static int[][] parent;
    private static int[][] maxWeight;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];
        int u, v, w;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v, w);
        }

        depth = new int[N + 1];
        parent = new int[19][N + 1];
        maxWeight = new int[19][N + 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        long mst = kruskal(edges);

        dfs(1, 1);
        fillParent();

        StringBuilder sb = new StringBuilder();
        for (Edge edge: edges) {
            sb.append(mst + edge.w - getMaxWeight(edge.u, edge.v)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static long getMaxWeight(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        long res = 0;
        for (int i = 18; i >= 0; --i) {
            if (depth[a] - (1 << i) >= depth[b]) {
                res = Math.max(res, maxWeight[i][a]);
                a = parent[i][a];
            }
        }

        if (a != b) {
            for (int i = 18; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    res = Math.max(res, maxWeight[i][a]);
                    res = Math.max(res, maxWeight[i][b]);
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            res = Math.max(res, maxWeight[0][a]);
            res = Math.max(res, maxWeight[0][b]);
        }
        return res;
    }

    private static void dfs(int curr, int dep) {
        depth[curr] = dep;
        for (Node next: graph.get(curr)) {
            if (depth[next.idx] > 0)
                continue;
            parent[0][next.idx] = curr;
            maxWeight[0][next.idx] = next.weight;
            dfs(next.idx, dep + 1);
        }
    }

    private static void fillParent() {
        for (int i = 1; i <= 18; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                maxWeight[i][j] = Math.max(maxWeight[i - 1][j], maxWeight[i - 1][parent[i - 1][j]]);
            }
        }
    }

    private static long kruskal(Edge[] edges) {
        Edge[] sorted = edges.clone();
        Arrays.sort(sorted, Comparator.comparingInt(o -> o.w));

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; ++i)
            parent[i] = i;

        long mst = 0;
        for (Edge edge: sorted) {
            if (find(parent, edge.u) == find(parent, edge.v))
                continue;
            mst += edge.w;
            union(parent, edge.u, edge.v);
            graph.get(edge.u).add(new Node(edge.v, edge.w));
            graph.get(edge.v).add(new Node(edge.u, edge.w));
        }
        return mst;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}