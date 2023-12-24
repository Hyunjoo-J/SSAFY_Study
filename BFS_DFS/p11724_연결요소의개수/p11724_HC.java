package BFS_DFS.p11724_연결요소의개수;

import java.io.*;
import java.util.*;

public class p11724_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		int answer = 0;
		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= N; ++i) {
			if (!visited[i]) {
				dfs(graph, i, visited);
				++answer;
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static void dfs(List<List<Integer>> graph, int x, boolean[] visited) {
		visited[x] = true;
		for (int next: graph.get(x)) {
			if (!visited[next])
				dfs(graph, next, visited);
		}
	}
}