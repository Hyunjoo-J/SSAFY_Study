package DynamicProgramming.p20303_할로윈의양아치;

import java.io.*;
import java.util.*;

// knapsack

/**
 * union find
 */
public class p20303_HC {

	private static int C, K;
	private static int[] wgh;
	private static int[] val;

	public static void main(String[] args) throws Exception {
		inputAndPreprocessing();
		int[][] dp = new int[C + 1][K];
		for (int i = 1; i <= C; ++i) {
			for (int j = 0; j < K; ++j) {
				if (j >= wgh[i])
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wgh[i]] + val[i]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println(dp[C][K - 1]);
	}

	private static void inputAndPreprocessing() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] c = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		int[] parent = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			parent[i] = i;
		}

		for (int i = 0, a, b; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(parent, a, b);
		}

		int n = 0;
		int[] count = new int[N + 1];
		int[] candy = new int[N + 1];
		for (int i = 1, x; i <= N; ++i) {
			x = find(parent, i);
			candy[x] += c[i];
			if (++count[x] == 1)
				++n;
		}

		int[] w = new int[n + 1];
		int[] v = new int[n + 1];
		for (int i = 1, idx = 1; i <= N; ++i) {
			if (count[i] > 0) {
				w[idx] = count[i];
				v[idx++] = candy[i];
			}
		}
		C = n;
		wgh = w;
		val = v;
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
}

/**
 * 그래프 탐색
 */
class p20303_HC_GraphTraversal {

	private static int C, K;
	private static int[] wgh;
	private static int[] val;

	public static void main(String[] args) throws Exception {
		inputAndPreprocessing();
		int[][] dp = new int[C + 1][K];
		for (int i = 1; i <= C; ++i) {
			for (int j = 0; j < K; ++j) {
				if (j >= wgh[i])
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wgh[i]] + val[i]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println(dp[C][K - 1]);
	}

	private static void inputAndPreprocessing() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] c = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		List<List<Integer>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0, a, b; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		int n = 0;
		int[] w = new int[N + 1];
		int[] v = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		for (int i = 1, idx = 1; i <= N; ++i) {
			if (visited[i])
				continue;
			++n;
			int[] res = bfs(i, graph, visited, c);
			w[idx] = res[0];
			v[idx++] = res[1];
		}

		C = n;
		wgh = Arrays.copyOfRange(w, 0, n + 1);
		val = Arrays.copyOfRange(v, 0, n + 1);
		br.close();
	}

	private static int[] bfs(int start, List<List<Integer>> graph, boolean[] visited, int[] c) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		int child = 0;
		int candy = 0;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			++child;
			candy += c[curr];
			for (int next: graph.get(curr)) {
				if (visited[next])
					continue;
				queue.add(next);
				visited[next] = true;
			}
		}
		return new int[] {child, candy};
	}
}