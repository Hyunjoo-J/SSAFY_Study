package MST.p23743_방탈출;

import java.io.*;
import java.util.*;

public class p23743_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[N + M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			edges[M + i] = new Edge(0, i + 1, Integer.parseInt(st.nextToken()));
		}

		int[] parent = new int[N + 1];
		for (int i = 0; i <= N; ++i) {
			parent[i] = i;
		}

		long answer = 0;
		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.cost, o2.cost));
		for (Edge edge: edges) {
			if (find(parent, edge.a) == find(parent, edge.b))
				continue;

			answer += edge.cost;
			union(parent, edge.a, edge.b);
		}
		System.out.println(answer);
		br.close();
	}

	private static void union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static int find(int[] parent, int x) {
		if (parent[x] != x)
			parent[x] = find(parent, parent[x]);
		return parent[x];
	}

	private static class Edge {
		int a, b, cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
	}
}

