package Tree.p1967_트리의지름;

import java.io.*;
import java.util.*;

public class p1967_HC {

	private static int n;
	private static List<List<Node>> graph;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		visited = new boolean[n + 1];
		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0, a, b, c; i < n - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		int node = dfs(1, 0)[0];
		int answer = dfs(node, 0)[1];

		System.out.println(answer);
		br.close();
	}

	private static int[] dfs(int curr, int length) {
		visited[curr] = true;	// check-in
		int[] temp = { curr, length };
		for (Node next: graph.get(curr)) {
			if (visited[next.idx])
				continue;
			int[] res = dfs(next.idx, length + next.weight);
			if (temp[1] < res[1]) {
				temp[1] = res[1];
				temp[0] = res[0];
			}
		}
		visited[curr] = false;	// check-out
		return temp;
	}

	private static class Node {
		int idx, weight;

		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
}
