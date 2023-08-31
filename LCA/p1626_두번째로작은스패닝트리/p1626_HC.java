package LCA.p1626_두번째로작은스패닝트리;

import java.io.*;
import java.util.*;

/**
 * LCA 응용 + MST + Disjoint Set
 * 사전 문제 : https://www.acmicpc.net/problem/15481
 *
 * 처리해야 할 부분
 *   1. MST가 아예 존재하지 않는 경우가 있다.
 *   2. 두 번째 MST가 없을 수도 있다.
 *   3. MST에 해당하지 않던 Edge를 추가해도, MST와 값이 같을 수 있다.
 *
 */
public class p1626_HC {

	private static class Edge {
		int u, v, weight;
		boolean mstEdge;

		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
			this.mstEdge = false;
		}
	}

	private static class Node {
		int idx, weight;

		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}

	private static int V, P;
	private static List<List<Node>> graph;
	private static Edge[] edges;
	private static int[] depth;
	private static int[][] parent;
	private static int[][] maxWeight;
	private static int[][] secMaxWeight;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		P = 1;
		while ((1 << P) <= V)
			++P;

		depth = new int[V + 1];
		parent = new int[P + 1][V + 1];
		maxWeight = new int[P + 1][V + 1];
		secMaxWeight = new int[P + 1][V + 1];

		graph = new ArrayList<>(V + 1);
		for (int i = 0; i <= V; ++i) {
			graph.add(new ArrayList<>());
		}

		edges = new Edge[E];
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		bw.write("" + solve());
		bw.flush();
		bw.close();
		br.close();
	}

	private static long solve() {
		long mstLength = kruskal(edges, graph);
		if (mstLength == -1)
			return -1;

		bfs(1);
		fillParent();

		long res = Long.MAX_VALUE;
		int candidate;
		for (Edge edge: edges) {
			if (edge.mstEdge)
				continue;
			if ((candidate = findMaxWeight(edge.u, edge.v, edge.weight)) != -1) {
				res = Math.min(res, mstLength - candidate + edge.weight);
			}
		}
		if (res == Long.MAX_VALUE)
			return -1;
		return res;
	}

	// LCA
	private static int findMaxWeight(int a, int b, int weight) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		int res = -1;
		for (int i = P; i >= 0; --i) {
			if (depth[a] - (1 << i) >= depth[b]) {
				res = Math.max(res, getValue(maxWeight[i][a], secMaxWeight[i][a], weight));
				a = parent[i][a];
			}
		}

		if (a != b) {
			for (int i = P; i >= 0; --i) {
				if (parent[i][a] != parent[i][b]) {
					res = Math.max(res, getValue(maxWeight[i][a], secMaxWeight[i][a], weight));
					res = Math.max(res, getValue(maxWeight[i][b], secMaxWeight[i][b], weight));
					a = parent[i][a];
					b = parent[i][b];
				}
			}
			res = Math.max(res, getValue(maxWeight[0][a], secMaxWeight[0][a], weight));
			res = Math.max(res, getValue(maxWeight[0][b], secMaxWeight[0][b], weight));
		}
		return res;
	}

	private static int getValue(int v1, int v2, int weight) {
		if (v1 != weight)
			return v1;
		else if (v2 != -1)
			return v2;
		return -1;
	}

	private static void fillParent() {
		int max, sec, paMax, paSec;
		for (int i = 1; i <= P; ++i) {
			for (int j = 1; j <= V; ++j) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				max = maxWeight[i - 1][j];
				sec = secMaxWeight[i - 1][j];
				paMax = maxWeight[i - 1][parent[i - 1][j]];
				paSec = secMaxWeight[i - 1][parent[i - 1][j]];
				if (max > paMax) {
					maxWeight[i][j] = max;
					secMaxWeight[i][j] = Math.max(paMax, sec);
				} else if (max < paMax) {
					maxWeight[i][j] = paMax;
					secMaxWeight[i][j] = Math.max(max, paSec);
				} else {
					maxWeight[i][j] = max;
					secMaxWeight[i][j] = Math.max(sec, paSec);
				}
			}
		}
	}

	private static void bfs(int root) {
		Queue<Integer> queue = new ArrayDeque<>(10);
		queue.add(root);
		depth[root] = 1;

		int curr;
		while (!queue.isEmpty()) {
			curr = queue.poll();

			for (Node next: graph.get(curr)) {
				if (depth[next.idx] > 0)
					continue;
				queue.add(next.idx);
				depth[next.idx] = depth[curr] + 1;
				parent[0][next.idx] = curr;
				maxWeight[0][next.idx] = next.weight;
				secMaxWeight[0][next.idx] = -1;
			}
		}
	}

	// MST
	private static long kruskal(Edge[] edges, List<List<Node>> graph) {
		int[] parent = new int[V + 1];
		for (int i = 1; i <= V; ++i)
			parent[i] = i;

		Arrays.sort(edges, Comparator.comparingInt(o -> o.weight));

		int count = 0;
		long mstLength = 0;
		for (Edge edge: edges) {
			if (count == V - 1)
				break;
			if (find(parent, edge.u) == find(parent, edge.v))
				continue;

			++count;
			edge.mstEdge = true;
			union(parent, edge.u, edge.v);
			mstLength += edge.weight;
			graph.get(edge.u).add(new Node(edge.v, edge.weight));
			graph.get(edge.v).add(new Node(edge.u, edge.weight));
		}

		if (count != V - 1)
			return -1;
		return mstLength;
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