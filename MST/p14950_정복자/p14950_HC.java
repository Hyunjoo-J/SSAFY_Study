package MST.p14950_정복자;

import java.io.*;
import java.util.*;

public class p14950_HC {

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

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		int[] parent = new int[N + 1];
		for (int i = 1; i <= N; ++i)
			parent[i] = i;

		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.cost, o2.cost));;

		long answer = 0;
		int cnt = 0;
		for (Edge edge: edges) {
			if (find(parent, edge.a) == find(parent, edge.b))
				continue;
			answer += edge.cost + (t * cnt++);
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
