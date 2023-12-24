package BFS_DFS.p7562_나이트의이동;

import java.io.*;
import java.util.*;

public class p7562_HC {

	private static final int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
	private static final int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int l, sx, sy, tx, ty;
		boolean[][] visited = new boolean[303][303];
		Queue<Pair> queue = new ArrayDeque<>();

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			// input
			l = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			tx = Integer.parseInt(st.nextToken());
			ty = Integer.parseInt(st.nextToken());

			// bfs
			queue.add(new Pair(sx, sy));
			visited[sx][sy] = true;

			int answer = -1;
			while (!queue.isEmpty()) {
				++answer;
				int queueSize = queue.size();
				for (int q = 0; q < queueSize; ++q) {
					Pair p = queue.remove();

					if (p.x == tx && p.y == ty) {
						queue.clear();
						break;
					}

					int nx, ny;
					for (int i = 0; i < 8; ++i) {
						nx = p.x + dx[i];
						ny = p.y + dy[i];

						if (nx < 0 || ny < 0 || nx >= l || ny >= l)
							continue;
						if (visited[nx][ny])
							continue;
						queue.add(new Pair(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			sb.append(answer).append("\n");

			// init
			for (int i = 0; i < l; ++i) {
				Arrays.fill(visited[i], 0, l, false);
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}