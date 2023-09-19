package BFS_DFS.p1707_이분그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p1707_HC {

    private static class Node {
        int idx;
        Node next;
    }

    private static int pIdx;
    private static Node[] pool;
    private static Node[] graph;
    private static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        graph = new Node[20001];
        visited = new int[20001];
        pool = new Node[400040];
        for (int i = 0; i < 400040; ++i)
            pool[i] = new Node();

        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            initialize(V);

            for (int i = 0, u, v; i < E; ++i) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                insert(u, v);
                insert(v, u);
            }

            boolean bipartite = true;
            for (int i = 1; i <= V && bipartite; ++i) {
                if (visited[i] == 0) {
                    bipartite = bfs(i);
                }
            }
            sb.append(bipartite ? "YES\n" : "NO\n");
        }
        System.out.println(sb.toString());
    }

    private static void initialize(int n) {
        pIdx = 0;
        Arrays.fill(graph, 0, n + 1, null);
        Arrays.fill(visited, 0, n + 1, 0);
    }

    private static boolean bfs(int x) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);

        visited[x] = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Node p = graph[now]; p != null; p = p.next) {
                if (visited[p.idx] == 0) {
                    visited[p.idx] = -visited[now];
                    queue.add(p.idx);
                } else if (visited[p.idx] == visited[now]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void insert(int u, int v) {
        Node node = pool[pIdx++];
        node.idx = v;
        node.next = graph[u];
        graph[u] = node;
    }
}
