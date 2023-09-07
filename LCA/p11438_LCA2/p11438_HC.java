package LCA.p11438_LCA2;

import java.io.*;
import java.util.*;

public class p11438_HC {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static int[] depth;
	private static int[][] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		parent = new int[18][N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		int a, b;
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		getDepth(1, 1);
		fillParent();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			bw.write(lca(a, b) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		for (int i = 17; i >= 0; --i) {
			if (depth[a] - (1 << i) >= depth[b]) {
				a = parent[i][a];
			}

			if (depth[a] == depth[b]) {
				break;
			}
		}

		int res = a;
		if (a != b) {
			for (int i = 17; i >= 0; --i) {
				if (parent[i][a] != parent[i][b]) {
					a = parent[i][a];
					b = parent[i][b];
				}
			}
			res = parent[0][a];
		}
		return res;
	}

	private static void fillParent() {
		for (int i = 1; i <= 17; ++i) {
			for (int j = 1; j <= N; ++j) {
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
	}

	private static void getDepth(int curr, int dep) {
		depth[curr] = dep;
		for (int next: graph.get(curr)) {
			if (depth[next] == 0) {
				parent[0][next] = curr;
				getDepth(next, dep + 1);
			}
		}
	}
}