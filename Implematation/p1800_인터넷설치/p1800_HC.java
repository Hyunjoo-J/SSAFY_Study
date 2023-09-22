package Implematation.p1800_인터넷설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1800_HC {

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	private static int N, K;
	private static List<List<Node>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < P; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		boolean flag = false;
		int left = 0;
		int right = 1000000;
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (dijkstra(mid)) {
				flag = true;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(flag ? right + 1 : -1);
		br.close();
	}

	private static boolean dijkstra(int w) {
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		heap.add(new Node(1, 0));
		distance[1] = 0;

		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.idx] < currNode.cost)
				continue;

			int cost;
			for (Node nextNode: graph.get(currNode.idx)) {
				cost = currNode.cost + (nextNode.cost > w ? 1 : 0);
				if (distance[nextNode.idx] > cost) {
					distance[nextNode.idx] = cost;
					heap.add(new Node(nextNode.idx, cost));
				}
			}
		}
		return distance[N] <= K;
	}
}
