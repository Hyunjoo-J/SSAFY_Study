/**
 * Diamond 5
 * LCA + Sparse Table + Segment Tree
 *
 * O((N+Q)logN)
 */

package SegmentTree.p18196_정기모임;

import java.io.*;
import java.util.*;

public class p18196_HC {

    private static class Node {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    private static int N, K, S;
    private static List<List<Node>> graph;
    private static int[] depth;
    private static int[][] parent;
    private static int[][] maxWeight;
    private static int[] tree;

    public static void main(String[] args) throws Exception {
        int[][] queries = input();

        // LCA
        bfs(1);
        fillParent();

        int[] distToNextVertex = new int[N];
        for (int i = 1; i < N; ++i) {
            distToNextVertex[i] = findMaxWeight(i, i + 1);
        }

        // Segment Tree
        buildSegmentTree(distToNextVertex);
        StringBuilder sb = new StringBuilder();
        for (int[] q: queries) {
            sb.append(query(1, 0, S - 1, q[0], q[1] - 1)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return Math.max(query(node << 1, left, mid, queryLeft, queryRight),
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
    }

    private static void buildSegmentTree(int[] arr) {
        for (int i = 0; i < N; ++i) {
            tree[S + i] = arr[i];
        }
        for (int i = S - 1; i > 0; --i) {
            tree[i] = Math.max(tree[i << 1], tree[(i << 1) | 1]);
        }
    }

    private static int findMaxWeight(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int res = -1;
        for (int i = K; i >= 0; --i) {
            if ((depth[u] - (1 << i)) >= depth[v]) {
                res = Math.max(res, maxWeight[i][u]);
                u = parent[i][u];
            }
        }

        if (u != v) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][u] != parent[i][v]) {
                    res = Math.max(res, maxWeight[i][u]);
                    res = Math.max(res, maxWeight[i][v]);
                    u = parent[i][u];
                    v = parent[i][v];
                }
            }
            res = Math.max(res, maxWeight[0][u]);
            res = Math.max(res, maxWeight[0][v]);
        }
        return res;
    }

    private static void fillParent() {
        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                maxWeight[i][j] = Math.max(maxWeight[i - 1][j], maxWeight[i - 1][parent[i - 1][j]]);
            }
        }
    }

    private static void bfs(int root) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        depth[root] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node nextNode: graph.get(now)) {
                if (depth[nextNode.idx] > 0)
                    continue;
                depth[nextNode.idx] = depth[now] + 1;
                parent[0][nextNode.idx] = now;
                maxWeight[0][nextNode.idx] = nextNode.weight;
                queue.add(nextNode.idx);
            }
        }
    }

    private static int[][] input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        K = 1;
        while ((1 << K) <= N)
            ++K;

        S = 1;
        while (S < N)
            S <<= 1;

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];
        maxWeight = new int[K + 1][N + 1];
        tree = new int[S << 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        for (int i = 0, A, B, C; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        int[][] queries = new int[Q][2];
        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        return queries;
    }
}