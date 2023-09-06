package Dijkstra.p11779_최소비용구하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p11779_MJ {

	static class Node implements Comparable<Node> {
		int to;
		int cost;

		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node e) {
			return Integer.compare(this.cost, e.cost);
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, start, end, dist[];
	static ArrayList<Node> list[];
	static ArrayList<Integer> route[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		list = new ArrayList[n + 1];
		route = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<>();
			route[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list[from].add(new Node(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();
		System.out.println(dist[end]);
		System.out.println(route[end].size());
		for (int i = 0; i < route[end].size(); i++) {
			System.out.print(route[end].get(i) + " ");
		}

		br.close();
	}

	private static void dijkstra() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean visited[] = new boolean[n + 1];
		queue.offer(new Node(start, 0));
		dist[start] = 0;
		route[start].add(start);

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.to;

			if (visited[cur])
				continue;
			visited[cur] = true;

			for (Node node : list[cur]) {
				if (dist[node.to] > dist[cur] + node.cost) {
					dist[node.to] = dist[cur] + node.cost;
					route[node.to] = (ArrayList<Integer>) route[cur].clone();
					route[node.to].add(node.to);
					queue.offer(new Node(node.to, dist[node.to]));
				}
			}
		}
	}
}
