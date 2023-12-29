package Dijkstra.p1916_최소비용구하기;

import java.io.*;
import java.util.*;

public class p1916_HC {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);

		// dijkstra
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		heap.add(new Node(start, 0));
		distance[start] = 0;
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
		System.out.println(distance[end]);
	    br.close();
	}

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}
