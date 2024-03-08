package BFS_DFS.프로그래머스LV2_1829_카카오프렌즈컬러링북;

import java.util.*;

class Solution_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	private int m;
	private int n;
	private int[][] picture;

	public int[] solution(int m, int n, int[][] picture) {
		this.m = m;
		this.n = n;
		this.picture = picture;

		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (picture[i][j] == 0 || visited[i][j])
					continue;
				int size = bfs(i, j, visited);
				++numberOfArea;
				maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private int bfs(int x, int y, boolean[][] visited) {
		int number = picture[x][y];
		int size = 0;
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			++size;

			for (int i = 0; i < 4; ++i) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= m || ny >= n)
					continue;
				if (visited[nx][ny])
					continue;
				if (picture[nx][ny] != number)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return size;
	}

	private static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}