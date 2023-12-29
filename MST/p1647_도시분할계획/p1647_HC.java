package MST.p1647_도시분할계획;

import java.io.*;
import java.util.*;

public class p1647_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] parent = new int[N + 1];
		Edge[] edges = new Edge[M];

		for (int i = 0; i <= N; ++i) {
			parent[i] = i;
		}

		for (int i = 0, A, B, C; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(A, B, C);
		}

		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.cost, o2.cost));

		// kruskal
		int mstLength = 0;
		int maxCost = 0;
		for (Edge edge: edges) {
			if (find(parent, edge.a) == find(parent, edge.b)) {
				continue;
			}
			mstLength += edge.cost;
			maxCost = edge.cost;
			union(parent, edge.a, edge.b);
		}
		System.out.println(mstLength - maxCost);
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
