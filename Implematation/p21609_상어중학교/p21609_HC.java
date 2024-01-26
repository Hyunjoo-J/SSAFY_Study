package Implematation.p21609_상어중학교;

import java.io.*;
import java.util.*;

public class p21609_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static final int EMPTY = -9;

	private static int N;
	private static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int score = 0;

		int[][] remove;
		while ((remove = findLargestBlockGroup()) != null) {	// 1
			score += remove.length * remove.length;
			removeBlock(remove);		// 2
			gravity();		// 3
			rotateCounterclockwise90();	// 4
			gravity();		// 5
		}
		System.out.println(score);
		br.close();
	}

	private static void gravity() {
		Deque<Integer> stack = new ArrayDeque<>(N);
		for (int j = 0, i; j < N; ++j) {
			for (i = 0; i < N; ++i) {
				if (graph[i][j] > -1) {
					stack.push(graph[i][j]);
					graph[i][j] = EMPTY;
				} else if (graph[i][j] == -1) {
					int ii = i;
					while (!stack.isEmpty()) {
						graph[--ii][j] = stack.pop();
					}
				}
			}
			while (!stack.isEmpty()) {
				graph[--i][j] = stack.pop();
			}
		}
	}

	private static void rotateCounterclockwise90() {
		int n = graph.length;
		int[][] rotated = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				rotated[i][j] = graph[j][n - i - 1];
			}
		}
		graph = rotated;
	}

	private static void removeBlock(int[][] remove) {
		for (int[] r: remove) {
			graph[r[0]][r[1]] = EMPTY;
		}
	}

	private static int[][] findLargestBlockGroup() {
		boolean[][] visited = new boolean[N][N];
		BlockGroup largest = new BlockGroup(-1, -1, -1, -1, null);
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (visited[i][j] || graph[i][j] <= 0)
					continue;
				BlockGroup find = findBlockGroup(i, j, visited);
				if (largest.compareTo(find) < 0)
					largest = find;
				visitedRainbowClear(visited);
			}
		}
		return largest.size > 1 ? largest.blocks : null;
	}

	private static BlockGroup findBlockGroup(int x, int y, boolean[][] visited) {
		int color = graph[x][y];

		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		visited[x][y] = true;

		int size = 0;
		int rainbow = 0;
		List<Pair> pairs = new ArrayList<>();

		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			++size;
			if (graph[p.x][p.y] == 0)
				++rainbow;
			pairs.add(p);

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (graph[nx][ny] <= -1)
					continue;
				if (graph[nx][ny] > 0 && graph[nx][ny] != color)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return new BlockGroup(
			size, rainbow, x, y, pairs.stream()
				.map(pair -> new int[] {pair.x, pair.y})
				.toArray(int[][]::new)
		);
	}

	private static void visitedRainbowClear(boolean[][] visited) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (graph[i][j] == 0)
					visited[i][j] = false;
			}
		}
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class BlockGroup implements Comparable<BlockGroup> {
		int size, rainbow, row, col;
		int[][] blocks;

		BlockGroup(int size, int rainbow, int row, int col, int[][] blocks) {
			this.size = size;
			this.rainbow = rainbow;
			this.row = row;
			this.col = col;
			this.blocks = blocks;
		}

		@Override
		public int compareTo(BlockGroup o2) {
			if (this.size != o2.size)
				return Integer.compare(this.size, o2.size);
			if (this.rainbow != o2.rainbow)
				return Integer.compare(this.rainbow, o2.rainbow);
			if (this.row != o2.row)
				return Integer.compare(this.row, o2.row);
			return Integer.compare(this.col, o2.col);
		}
	}
}
