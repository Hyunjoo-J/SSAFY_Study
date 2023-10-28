package Dijkstra.p9370_미확인도착지;

import java.io.*;
import java.util.*;

public class p9370_HC {

    private static final int INF = Integer.MAX_VALUE >> 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>(2001);
        for (int i = 0; i < 2001; ++i) {
            graph.add(new ArrayList<>());
        }
        int[] distS = new int[2001];
        int[] distG = new int[2001];
        int[] distH = new int[2001];
        int[] candidate = new int[101];

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; ++i)
                graph.get(i).clear();

            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            dijkstra(graph, n, s, distS);
            dijkstra(graph, n, g, distG);
            dijkstra(graph, n, h, distH);

            int cnt = 0;
            for (int i = 0; i < t; ++i) {
                int x = Integer.parseInt(br.readLine());
                int dist1 = distS[g] + distG[h] + distH[x];
                int dist2 = distS[h] + distH[g] + distG[x];
                if (distS[x] == dist1 || distS[x] == dist2) {
                    candidate[cnt++] = x;
                }
            }
            Arrays.sort(candidate, 0, cnt);
            for (int i = 0; i < cnt; ++i)
                sb.append(candidate[i]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int[] dijkstra(List<List<Node>> graph, int n, int start, int[] distance) {
        Arrays.fill(distance, 0, n + 1, INF);
        distance[start] = 0;
        Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        heap.add(new Node(start, 0));
        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            if (distance[currNode.idx] < currNode.cost)
                continue;

            int cost;
            for (Node nextNode: graph.get(currNode.idx)) {
                cost = currNode.cost + nextNode.cost;
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Node(nextNode.idx, cost));
                }
            }
        }
        return distance;
    }

    private static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}