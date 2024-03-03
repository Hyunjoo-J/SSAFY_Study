package Dijkstra.프로그래머스LV3_72413_합승택시요금;

import java.util.*;

class Solution_HC {

    private static final int INF = 123456789;
    private int n;
    private List<List<Node>> graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int[] fare: fares) {
            graph.get(fare[0]).add(new Node(fare[1], fare[2]));
            graph.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        int[] distanceFromS = dijkstra(s);
        int[] distanceFromA = dijkstra(a);
        int[] distanceFromB = dijkstra(b);
        int answer = INF;
        for (int i = 1; i <= n; ++i) {
            int cost = distanceFromS[i] + distanceFromA[i] + distanceFromB[i];
            answer = Math.min(answer, cost);
        }
        return answer;
    }

    private int[] dijkstra(int x) {
        Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        heap.add(new Node(x, 0));
        distance[x] = 0;
        while (!heap.isEmpty()) {
            Node currNode = heap.poll();

            if (distance[currNode.index] < currNode.cost)
                continue;

            for (Node nextNode: graph.get(currNode.index)) {
                int cost = currNode.cost + nextNode.cost;

                if (distance[nextNode.index] > cost) {
                    distance[nextNode.index] = cost;
                    heap.add(new Node(nextNode.index, cost));
                }
            }
        }
        return distance;
    }

    private static class Node {
        int index, cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}