package Dijkstra.p2211_네트워크복구;

import java.io.*;
import java.util.*;

public class p2211_HC {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Node(B, C));
			graph.get(B).add(new Node(A, C));
		}

		// dijkstra
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		heap.add(new Node(1, 0));
		distance[1] = 0;
		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.idx] < currNode.cost)
				continue;

			for (Node nextNode: graph.get(currNode.idx)) {
				int cost = currNode.cost + nextNode.cost;

				if (distance[nextNode.idx] > cost) {
					distance[nextNode.idx] = cost;
					heap.add(new Node(nextNode.idx, cost));
				}
			}
		}

		// tracing
		boolean[] on = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		on[1] = true;
		List<int[]> usedEdges = new ArrayList<>();
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (Node nextNode: graph.get(curr)) {
				if (distance[nextNode.idx] - distance[curr] == nextNode.cost) {
					if (on[nextNode.idx] == false) {
						usedEdges.add(new int[]{curr, nextNode.idx});
						on[nextNode.idx] = true;
						queue.add(nextNode.idx);
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(usedEdges.size()).append("\n");
		for (int[] edge: usedEdges) {
			sb.append(edge[0]).append(" ")
				.append(edge[1]).append("\n");
		}
		System.out.println(sb.toString());
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
