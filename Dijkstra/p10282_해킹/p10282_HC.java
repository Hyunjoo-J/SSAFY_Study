package Dijkstra.p10282_해킹;

import java.io.*;
import java.util.*;

public class p10282_HC {

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] distance = new int[10001];
		List<List<Node>> graph;
		PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));;

		int n, d, c, a, b, s;

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>(n + 1);
			for (int i = 0; i <= n; ++i)
				graph.add(new ArrayList<>());

			for (int i = 0; i < d; ++i) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				graph.get(b).add(new Node(a, s));
			}

			Arrays.fill(distance, 0, n + 1, INF);
			heap.clear();
			heap.add(new Node(c, 0));
			distance[c] = 0;
			while (!heap.isEmpty()) {
				Node currNode = heap.poll();

				if (distance[currNode.idx] < currNode.cost) {
					continue;
				}

				int cost;
				for (Node nextNode: graph.get(currNode.idx)) {
					cost = currNode.cost + nextNode.cost;
					if (distance[nextNode.idx] > cost) {
						distance[nextNode.idx] = cost;
						heap.add(new Node(nextNode.idx, cost));
					}
				}
			}

			int cnt = 0, time = 0;
			for (int i = 1; i <= n; ++i) {
				if (distance[i] != INF) {
					++cnt;
					time = Math.max(time, distance[i]);
				}
			}
			sb.append(cnt).append(" ").append(time).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
