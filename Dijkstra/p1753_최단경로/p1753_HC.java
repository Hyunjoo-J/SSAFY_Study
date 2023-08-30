package Dijkstra.p1753_최단경로;

import java.io.*;
import java.util.*;

public class p1753_HC {

	private static final int INF = Integer.MAX_VALUE;

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>(V + 1);
		for (int i = 0; i <= V; ++i)
			graph.add(new ArrayList<>());

		int u, v, w;
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}

		// dijkstra
		int[] distance = new int[V + 1];
		Arrays.fill(distance, INF);
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		heap.add(new Node(K, 0));
		distance[K] = 0;

		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.idx] < currNode.cost)
				continue;

			int cost;
			for (Node nextNode : graph.get(currNode.idx)) {
				cost = currNode.cost + nextNode.cost;
				if (distance[nextNode.idx] > cost) {
					distance[nextNode.idx] = cost;
					heap.add(new Node(nextNode.idx, cost));
				}
			}
		}

		for (int i = 1; i <= V; ++i) {
			bw.write((distance[i] == INF ? "INF" : distance[i]) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
