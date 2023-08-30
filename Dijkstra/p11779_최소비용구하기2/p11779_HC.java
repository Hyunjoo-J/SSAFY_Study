package Dijkstra.p11779_최소비용구하기2;

import java.io.*;
import java.util.*;

public class p11779_HC {

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		List<List<Node>> graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i)
			graph.add(new ArrayList<>());

		int a, b, c;
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int[] distance = new int[n + 1];
		int[] prev = new int[n + 1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		heap.add(new Node(s, 0));

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
					prev[nextNode.idx] = currNode.idx;
				}
			}
		}

		Stack<Integer> stack = new Stack<>();
		int p = e;
		while (p != 0) {
			stack.push(p);
			p = prev[p];
		}

		StringBuilder sb = new StringBuilder();
		sb.append(distance[e]).append("\n")
			.append(stack.size()).append("\n");
		while (!stack.isEmpty())
			sb.append(stack.pop()).append(" ");
		System.out.println(sb.toString());
	    br.close();
	}
}
