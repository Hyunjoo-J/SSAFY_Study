package BFS_DFS.p17836_공주님을구해라;

import java.io.*;
import java.util.*;

public class p17836_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][][] visited = new boolean[N][M][2];
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(0, 0, 0, 0));
		visited[0][0][0] = true;

		int answer = -1;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			if (p.x == N - 1 && p.y == M - 1) {
				answer = p.t;
				break;
			}

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visited[nx][ny][p.sword])
					continue;
				if (p.sword == 0 && graph[nx][ny] == 1)
					continue;
				if (p.t >= T)
					continue;
				int nSword = graph[nx][ny] == 2 ? 1 : p.sword;
				queue.add(new Pair(nx, ny, p.t + 1, nSword));
				visited[nx][ny][nSword] = true;
			}
		}
		System.out.println(answer == -1 ? "Fail" : answer);
	    br.close();
	}

	private static class Pair {
		int x, y, t, sword;

		public Pair(int x, int y, int t, int sword) {
			this.x = x;
			this.y = y;
			this.t = t;
			this.sword = sword;
		}
	}
}
