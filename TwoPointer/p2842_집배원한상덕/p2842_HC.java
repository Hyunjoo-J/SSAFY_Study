package TwoPointer.p2842_집배원한상덕;

import java.io.*;
import java.util.*;

public class p2842_HC {

	private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
	private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	private static int N;
	private static int[][] height;
	private static boolean[][] house;
	private static boolean[][] visited;
	private static int sx, sy, totalHouse;
	private static Queue<Pair> gQueue;

	public static void main(String[] args) throws Exception {
		int[] heights = input();
		int length = heights.length;
		int low = 0;
		int high = 0;
		int answer = Integer.MAX_VALUE;

		while (low <= high && high < length) {
			if (bfs(heights[low], heights[high])) {
				answer = Math.min(answer, heights[high] - heights[low]);
				++low;
			} else {
				++high;
			}
		}
		System.out.println(answer);
	}

	private static boolean bfs(int min, int max) {
		if (height[sx][sy] < min || height[sx][sy] > max)
			return false;

		Queue<Pair> queue = gQueue;
		for (int i = 0; i < N; ++i)
			Arrays.fill(visited[i], false);

		queue.add(new Pair(sx, sy));
		visited[sx][sy] = true;
		int nx, ny, houseCnt = 0;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			if (house[p.x][p.y])
				++houseCnt;

			for (int i = 0; i < 8; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (height[nx][ny] < min || height[nx][ny] > max)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return houseCnt == totalHouse;
	}

	private static int[] input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		height = new int[N][N];
		house = new boolean[N][N];
		visited = new boolean[N][N];
		gQueue = new ArrayDeque<>(N << 1);

		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				if (line.charAt(j) == 'K') {
					house[i][j] = true;
					++totalHouse;
				} else if (line.charAt(j) == 'P') {
					sx = i;
					sy = j;
				}
			}
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				height[i][j] = Integer.parseInt(st.nextToken());
				set.add(height[i][j]);
			}
		}
		br.close();

		return new ArrayList<>(set).stream()
			.sorted()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
