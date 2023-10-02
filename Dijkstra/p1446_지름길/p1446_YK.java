package Dijkstra.p1446_지름길;

import java.io.*;
import java.util.*;
public class p1446_YK {

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, D;
    static List<Node>[] list;
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        list = new ArrayList[D + 1];
        dist = new int[D + 1];
        for (int i = 0; i < D + 1; ++i) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (end > D) continue; // 지름길이 끝나는 지점이 도착 지점 이후
            if (weight >= end - start) continue; // 지름길이 더 걸리는 길이면
            list[end].add(new Node(start, weight));
        }

        dijkstra();

        System.out.println(dist[D]);
        br.close();
    }

    private static void dijkstra() {
        boolean[] visited = new boolean[D + 1];
        dist[0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            if (list[cur.vertex].size() > 0) {
                for (Node next : list[cur.vertex]) {
                    if (dist[cur.vertex] > dist[next.vertex] + next.weight) {
                        dist[cur.vertex] = Math.min(dist[cur.vertex - 1] + 1, dist[next.vertex] + next.weight);
                        pq.offer(next);
                    }
                }
            } else {
                if (cur.vertex != 0) dist[cur.vertex] = dist[cur.vertex - 1] + 1;
            }

            if (cur.vertex < D) pq.offer(new Node(cur.vertex + 1, dist[cur.vertex] + 1));
        }
    }
}
