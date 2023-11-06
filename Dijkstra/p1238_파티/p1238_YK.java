package Dijkstra.p1238_파티;

import java.io.*;
import java.util.*;

public class p1238_YK {

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int N, M, X;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] goList = new ArrayList[N + 1];
        ArrayList<Node>[] comeList = new ArrayList[N + 1];
        for (int i = 0; i <= N; ++i) {
            goList[i] = new ArrayList<>();
            comeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            goList[a].add(new Node(b, cost));
            comeList[b].add(new Node(a, cost));
        }

        int[] goDist = dijkstra(goList);
        int[] comeDist = dijkstra(comeList);

        int result = 0;
        for (int i = 1; i <= N; ++i) {
            result = Math.max(result, goDist[i] + comeDist[i]);
        }
        System.out.println(result);
        br.close();
    }

    private static int[] dijkstra(ArrayList<Node>[] list) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Node node : list[cur.v]) {
                if (dist[node.v] > dist[cur.v] + node.w) {
                    dist[node.v] = dist[cur.v] + node.w;
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }

        return dist;
    }
}
