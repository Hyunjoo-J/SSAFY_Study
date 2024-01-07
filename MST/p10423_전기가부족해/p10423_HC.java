package MST.p10423_전기가부족해;

import java.io.*;
import java.util.*;

public class p10423_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[M + K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			edges[i] = new Edge(0, Integer.parseInt(st.nextToken()), 0);
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[K + i] = new Edge(
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		int[] parent = new int[N + 1];
		for (int i = 0; i <= N; ++i) {
			parent[i] = i;
		}

		long answer = 0;
		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.w, o2.w));

		for (Edge edge: edges) {
			if (find(parent, edge.u) == find(parent, edge.v))
				continue;

			answer += edge.w;
			union(parent, edge.u, edge.v);
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
		int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}
