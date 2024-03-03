package BFS_DFS.프로그래머스LV3_49189_가장먼노드;

import java.util.*;

public class Solution_HC {

	public int solution(int n, int[][] edge) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; ++i)
			graph.add(new ArrayList<>());

		for (int[] e: edge) {
			int a = e[0];
			int b = e[1];
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		int farthest = 0;
		int[] visited = new int[n + 1];
		Queue<Integer> queue = new LinkedList<>();
		visited[1] = 1;
		queue.add(1);
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int next: graph.get(now)) {
				if (visited[next] != 0)
					continue;
				visited[next] = visited[now] + 1;
				farthest = Math.max(farthest, visited[next]);
				queue.add(next);
			}
		}

		int answer = 0;
		for (int i = 1; i <= n; ++i) {
			if (visited[i] == farthest)
				answer++;
		}
		return answer;
	}
}
