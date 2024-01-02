package Dijkstra.p1916_최소비용구하기;

import java.io.*;
import java.util.*;

public class p1916_YK {

    static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Node>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.to;

            if (visited[now]) continue;
            visited[now] = true;

            if (now == end) break;

            for (Node next : list[now]) {
                if (dist[next.to] > dist[now] + next.weight) {
                    dist[next.to] = dist[now] + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        System.out.println(dist[end]);
        br.close();
    }
}
