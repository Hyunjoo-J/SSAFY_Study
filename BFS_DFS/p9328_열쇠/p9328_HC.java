package BFS_DFS.p9328_열쇠;

import java.io.*;
import java.util.*;

public class p9328_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		char[][] graph = new char[102][102];
		int h, w;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			// input
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= h; ++i) {
				String line = br.readLine();
				for (int j = 1; j <= w; ++j) {
					graph[i][j] = line.charAt(j - 1);
				}
			}

			// padding
			for (int i = 0; i < h + 2; ++i) {
				graph[i][0] = '.';
				graph[i][w + 1] = '.';
			}
			Arrays.fill(graph[0], '.');
			Arrays.fill(graph[h + 1], '.');

			h += 2;
			w += 2;

			// convert key string to bit
			char[] keyAlphabets = br.readLine().toCharArray();
			int keys = 0;
			for (char c: keyAlphabets) {
				if (c == '0')
					break;
				keys |= (1 << (c - 'a'));
			}

			// bfs
			int nKeys = -1;
			int docs = -1;
			while (keys != nKeys) {
				nKeys = keys;
				int[] res = bfs(graph, h, w, keys);
				keys = res[0];
				docs = res[1];
			}
			sb.append(docs).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int[] bfs(char[][] graph, int h, int w, int keys) {
		Queue<Pair> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[h][w];
		queue.add(new Pair(0, 0));
		visited[0][0] = true;

		int docs = 0;
		int nx, ny;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w)
					continue;
				if (graph[nx][ny] == '*')
					continue;
				if (visited[nx][ny])
					continue;
				if ('A' <= graph[nx][ny] && graph[nx][ny] <= 'Z' && !hasKey(graph[nx][ny], keys))
					continue;
				else if ('a' <= graph[nx][ny] && graph[nx][ny] <= 'z')
					keys |= (1 << (graph[nx][ny] - 'a'));
				else if (graph[nx][ny] == '$')
					++docs;
				visited[nx][ny] = true;
				queue.add(new Pair(nx, ny));
			}
		}
		return new int[] {keys, docs};
	}

	private static boolean hasKey(char alphabet, int keys) {
		int b = alphabet - 'A';
		return (keys & (1 << b)) > 0;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
