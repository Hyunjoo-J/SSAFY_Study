package BFS_DFS.p16946_벽부수고이동하기4;

import java.io.*;
import java.util.*;

public class p16946_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static int N, M;
	private static int[][] graph;
	private static int[][] visited;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				graph[i][j] = line.charAt(j) - '0';
			}
		}

		List<Integer> count = new ArrayList<>(5000);
		count.add(-1);

		int num = 0;
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < M; ++y) {
				if (visited[x][y] == 0 && graph[x][y] == 0)
					count.add(bfs(x, y, ++num));
			}
		}

		int[][] answer = new int[N][M];
		int[] checker = new int[num + 1];
		Arrays.fill(checker, -1);

		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < M; ++y) {
				if (graph[x][y] == 0)
					continue;

				int adj = 0;
				for (int i = 0; i < 4; ++i) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!isRange(nx, ny))
						continue;
					if (graph[nx][ny] != 0)
						continue;
					int vis = visited[nx][ny];
					if (checker[vis] < x * M + y) {
						checker[vis] = x * M + y;
						adj += count.get(vis);
					}
				}
				answer[x][y] = (adj + 1) % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < M; ++y) {
				sb.append(answer[x][y]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int bfs(int x, int y, int num) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		visited[x][y] = num;

		int nx, ny, cnt = 0;
		while (!queue.isEmpty()) {
			++cnt;
			Pair p = queue.poll();

			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if (!isRange(nx, ny))
					continue;
				if (visited[nx][ny] != 0)
					continue;
				if (graph[nx][ny] != 0)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = num;
			}
		}
		return cnt;
	}

	private static boolean isRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
