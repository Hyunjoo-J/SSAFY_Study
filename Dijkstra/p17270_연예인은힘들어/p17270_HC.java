package Dijkstra.p17270_연예인은힘들어;

import java.io.*;
import java.util.*;

public class p17270_HC {

	private static final int INF = Integer.MAX_VALUE >> 2;

	private static int V;
	private static List<List<Node>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= V; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0, a, b, c; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] distJ = dijkstra(J);
		int[] distS = dijkstra(S);

		int minDist = Integer.MAX_VALUE;
		for (int i = 1; i <= V; ++i) {
			if (i == S || i == J)
				continue;
			minDist = Math.min(minDist, distJ[i] + distS[i]);
		}

		int minIdx = -1;
		for (int i = 1; i <= V; ++i) {
			if (i == S || i == J)   // 1
				continue;
			if (distJ[i] + distS[i] > minDist) // 2
				continue;
			if (distJ[i] > distS[i])    // 3
				continue;
			if (minIdx > -1 && distJ[minIdx] <= distJ[i])   // 4
				continue;
			minIdx = i;
		}
		System.out.println(minIdx);
		br.close();
	}

	private static int[] dijkstra(int start) {
		int[] distance = new int[V + 1];
		Arrays.fill(distance, INF);
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
