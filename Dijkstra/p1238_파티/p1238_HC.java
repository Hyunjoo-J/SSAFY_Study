package Dijkstra.p1238_파티;

import java.io.*;
import java.util.*;

public class p1238_HC {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>(N + 1);
		List<List<Node>> revGraph = new ArrayList<>(N + 1);
	    for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
			revGraph.add(new ArrayList<>());
		}

		for (int i = 0, S, D, T; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			graph.get(S).add(new Node(D, T));
			revGraph.get(D).add(new Node(S, T));
		}

		int[] go = dijkstra(graph, N, X);
		int[] back = dijkstra(revGraph, N, X);

		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			answer = Math.max(go[i] + back[i], answer);
		}
		System.out.println(answer);
		br.close();
	}

	private static int[] dijkstra(List<List<Node>> graph, int N, int start) {
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
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
