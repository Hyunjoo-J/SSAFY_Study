package LCA.p3176_도로네트워크;

import java.io.*;
import java.util.*;

public class p3176_HC {

    private static class Node {
        int idx, length;

        public Node(int idx, int length) {
            this.idx = idx;
            this.length = length;
        }
    }

    private static int N, P;
    private static int[] depth;
    private static int[][] parent;
    private static int[][] shortest;
    private static int[][] longest;
    private static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M, A, B, C, D, E;

        N = Integer.parseInt(br.readLine());
        P = 1;
        while ((1 << P) <= N) {
            ++P;
        }
        depth = new int[N + 1];
        parent = new int[P + 1][N + 1];
        shortest = new int[P + 1][N + 1];
        longest = new int[P + 1][N + 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= P; ++i) {
            Arrays.fill(shortest[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        bfs(1);
        fillParent();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            int[] res = findDistance(D, E);
            sb.append(res[0]).append(" ")
                .append(res[1]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int[] findDistance(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = P; i >= 0; --i) {
            if (depth[a] - (1 << i) >= depth[b]) {
                min = Math.min(min, shortest[i][a]);
                max = Math.max(max, longest[i][a]);
                a = parent[i][a];
            }
        }

        if (a != b) {
            for (int i = P; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    min = Math.min(min, shortest[i][a]);
                    min = Math.min(min, shortest[i][b]);
                    max = Math.max(max, longest[i][a]);
                    max = Math.max(max, longest[i][b]);
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            min = Math.min(min, shortest[0][a]);
            min = Math.min(min, shortest[0][b]);
            max = Math.max(max, longest[0][a]);
            max = Math.max(max, longest[0][b]);
        }
        return new int[] {min, max};
    }

    private static void bfs(int root) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        depth[root] = 1;

        int curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (Node nextNode: graph.get(curr)) {
                if (depth[nextNode.idx] > 0)
                    continue;
                depth[nextNode.idx] = depth[curr] + 1;
                parent[0][nextNode.idx] = curr;
                shortest[0][nextNode.idx] = nextNode.length;
                longest[0][nextNode.idx] = nextNode.length;
                queue.add(nextNode.idx);
            }
        }
    }

    private static void fillParent() {
        for (int i = 1; i <= P; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                shortest[i][j] = Math.min(shortest[i - 1][j], shortest[i - 1][parent[i - 1][j]]);
                longest[i][j] = Math.max(longest[i - 1][j], longest[i - 1][parent[i - 1][j]]);
            }
        }
    }
}
