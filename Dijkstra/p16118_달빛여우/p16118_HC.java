package Dijkstra.p16118_달빛여우;

import java.io.*;
import java.util.*;

public class p16118_HC {

	private static final int INF = Integer.MAX_VALUE;
	private static int N;
	private static List<List<Node>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0, a, b, d; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) << 1;
			graph.get(a).add(new Node(b, d));
			graph.get(b).add(new Node(a, d));
		}

		int[] distForFox = dijkstraForFox(1);
		int[] distForWolf = dijkstraForWolf(1);

		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			if (distForFox[i] < distForWolf[i])
				++answer;
		}
		System.out.println(answer);
		br.close();
	}

	private static int[] dijkstraForFox(int start) {
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		heap.add(new Node(start, 0));
		distance[start] = 0;

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
		return distance;
	}

	private static int[] dijkstraForWolf(int start) {
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[][] distance = new int[N + 1][2];
		for (int i = 0; i <= N; ++i)
			Arrays.fill(distance[i], INF);
		heap.add(new Node(start, 0, 1));
		distance[start][1] = 0;

		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.idx][currNode.run] < currNode.cost)
				continue;

			for (Node nextNode: graph.get(currNode.idx)) {
				int cost = currNode.cost + (currNode.run == 1 ? nextNode.cost >> 1 : nextNode.cost << 1);
				if (distance[nextNode.idx][1 - currNode.run] > cost) {
					distance[nextNode.idx][1 - currNode.run] = cost;
					heap.add(new Node(nextNode.idx, cost, 1 - currNode.run));
				}
			}
		}

		int[] _distance = new int[N + 1];
		for (int i = 0; i <= N; ++i) {
			_distance[i] = Math.min(distance[i][0], distance[i][1]);
		}
		return _distance;
	}

	private static class Node {
		int idx, cost, run;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		public Node(int idx, int cost, int run) {
			this.idx = idx;
			this.cost = cost;
			this.run = run;
		}
	}
}
