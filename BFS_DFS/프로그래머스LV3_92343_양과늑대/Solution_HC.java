package BFS_DFS.프로그래머스LV3_92343_양과늑대;

import java.util.*;

class Solution_HC {
	
	public int solution(int[] info, int[][] edges) {
		int n = info.length;
		int fullVisit = (1 << (n + 1)) - 1;

		List<List<Integer>> graph = new ArrayList<>(n + 1);
		for (int i = 0; i < n; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int[] edge: edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}

		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][fullVisit];
		queue.add(new Node(0, 1, 1 - info[0], info[0]));
		visited[0][1] = true;

		int answer = 0;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			answer = Math.max(answer, node.sheep);

			for (int next: graph.get(node.idx)) {
				int nVisit = node.visit | (1 << next);
				int nSheep = node.sheep;
				int nWolf = node.wolf;

				if ((node.visit & (1 << next)) == 0) {
					nSheep += (1 - info[next]);
					nWolf += info[next];
				}
				if (visited[next][nVisit])
					continue;
				if (nSheep <= nWolf)
					continue;

				queue.add(new Node(next, nVisit, nSheep, nWolf));
				visited[next][nVisit] = true;
			}
		}
		return answer;
	}

	private static class Node {
		int idx, visit, wolf, sheep;

		Node(int idx, int visit, int sheep, int wolf) {
			this.idx = idx;
			this.visit = visit;
			this.sheep = sheep;
			this.wolf = wolf;
		}
	}
}