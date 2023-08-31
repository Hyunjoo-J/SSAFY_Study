package MST.p16398_행성연결;

import java.io.*;
import java.util.*;

public class p16398_HC {

	private static class Edge {
		int a, b, cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Edge[] edges = new Edge[(N * (N - 1)) >> 1];
		for (int i = 0, idx = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; ++j)
				st.nextToken();
			for (int j = i + 1; j < N; ++j) {
				edges[idx++] = new Edge(i, j, Integer.parseInt(st.nextToken()));
			}
		}

		int[] parent = new int[N + 1];
		for (int i = 1; i <= N; ++i)
			parent[i] = i;

		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.cost, o2.cost));;

		long answer = 0;
		for (Edge edge: edges) {
			if (find(parent, edge.a) == find(parent, edge.b))
				continue;
			answer += edge.cost;
			union(parent, edge.a, edge.b);
		}
		System.out.println(answer);
		br.close();
	}

	private static int find(int[] parent, int x) {
		if (parent[x] != x)
			parent[x] = find(parent, parent[x]);
		return parent[x];
	}

	private static void union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}
}
