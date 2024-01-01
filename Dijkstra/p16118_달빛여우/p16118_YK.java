package Dijkstra.p16118_달빛여우;

import java.io.*;
import java.util.*;

public class p16118_YK {

    static class Node implements Comparable<Node>{
        int to;
        double weight;
        boolean isFast; // 다음 길을 빨리갈 예정인지?

        public Node(int to, double weight, boolean isFast) {
            this.to = to;
            this.weight = weight;
            this.isFast = isFast;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(weight, o.weight);
        }
    }

    static int N, M;
    static List<Node>[] list;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, d, false));
            list[b].add(new Node(a, d, false));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        double[] distFox = new double[N + 1];
        Arrays.fill(distFox, INF);
        boolean[] visited = new boolean[N + 1];

        distFox[1] = 0;
        pq.offer(new Node(1, 0, false));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.to;

            if (visited[now]) continue;
            visited[now] = true;

            for (Node next : list[now]) {
                if (distFox[next.to] > distFox[now] + next.weight) {
                    distFox[next.to] = distFox[now] + next.weight;
                    pq.offer(new Node(next.to, distFox[next.to], false));
                }
            }
        }

        double[] distFast = new double[N + 1]; // 직전에 빨리 감 (다음에 느리게 감)
        double[] distSlow = new double[N + 1]; // 직전에 느리게 감 (다음에 빠르게 감)
        boolean[] visitedFast = new boolean[N + 1];
        boolean[] visitedSlow = new boolean[N + 1];

        Arrays.fill(distFast, INF);
        Arrays.fill(distSlow, INF);

//        distFast[1] = 0; --> 다시 정점으로 돌아와 첫 부분에서 느리게 출발하는 게 나을 때도 있음
        distSlow[1] = 0; // 다음에 빠르게 갈 차례인 이 쪽에만 0으로 만들어 줘야함
        pq.offer(new Node(1, 0, true));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.to;
            boolean isFast = node.isFast;

            if (isFast && !visitedFast[now]) visitedFast[now] = true;
            else if (!isFast && !visitedSlow[now]) visitedSlow[now] = true;
            else continue;

            for (Node next : list[now]) {
                double weight = next.weight;

                if (node.isFast) {
                    weight /= 2;
                    if (distFast[next.to] > distSlow[now] + weight) {
                        distFast[next.to] = distSlow[now] + weight;
                        pq.offer(new Node(next.to, distFast[next.to], false));
                    }

                } else {
                    weight *= 2;
                    if (distSlow[next.to] > distFast[now] + weight) {
                        distSlow[next.to] = distFast[now] + weight;
                        pq.offer(new Node(next.to, distSlow[next.to], true));
                    }
                }
            }
        }

        int result = 0;
        for (int i = 2; i <= N; ++i) {
            if (distFox[i] < distFast[i] && distFox[i] < distSlow[i]) ++result;
        }

        System.out.println(result);
        br.close();
    }
}
