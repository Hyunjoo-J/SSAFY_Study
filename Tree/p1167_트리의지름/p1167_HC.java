package Tree.p1167_트리의지름;

import java.io.*;
import java.util.*;

public class p1167_HC {

	private static int V;
	private static List<List<Node>> graph;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		V = Integer.parseInt(br.readLine());
		visited = new boolean[V + 1];
		graph = new ArrayList<>(V + 1);
		for (int i = 0; i <= V; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0, a, b, c; i < V; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			for (int j = 0, end = st.countTokens() - 1; j < end; j += 2) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				graph.get(a).add(new Node(b, c));
				graph.get(b).add(new Node(a, c));
			}
		}

		int node = dfs(1, 0)[1];
		Arrays.fill(visited, false);

		int answer = dfs(node, 0)[0];
		System.out.println(answer);
	    br.close();
	}

	private static int[] dfs(int curr, int distance) {
		visited[curr] = true;

		int farthest = distance;
		int farthestNode = curr;
		for (Node nextNode: graph.get(curr)) {
			if (visited[nextNode.idx])
				continue;
			int[] res = dfs(nextNode.idx, distance + nextNode.distance);
			if (farthest < res[0]) {
				farthest = res[0];
				farthestNode = res[1];
			}
		}
		return new int[]{farthest, farthestNode};
	}

	private static class Node {
		int idx, distance;

		public Node(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}
	}
}