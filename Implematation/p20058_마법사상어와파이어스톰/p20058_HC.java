package Implematation.p20058_마법사상어와파이어스톰;

import java.io.*;
import java.util.*;

public class p20058_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static final int[][] temp = new int[64][64];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = 1 << Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[][] A = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] L = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; ++i) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < Q; ++i)
			firestorm(A, N, 1 << L[i]);

		System.out.println(getTotalSum(A, N) + "\n" + getBiggestChunkSize(A, N));
		br.close();
	}

	private static void firestorm(int[][] A, int N, int L) {
		// rotate
		for (int x = 0; x < N; x += L) {
			for (int y = 0; y < N; y += L) {
				int[][] rotated = rotate(A, x, y, L);
				mapCopy(rotated, A, x, y, L);
			}
		}

		// melt
		boolean[][] checker = new boolean[N][N];
		int nx, ny, adj;
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				if (A[x][y] == 0)
					continue;

				adj = 0;
				for (int i = 0; i < 4; ++i) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (A[nx][ny] > 0)
						++adj;
				}
				if (adj < 3)
					checker[x][y] = true;
			}
		}

		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				if (checker[x][y])
					A[x][y] -= 1;
			}
		}
	}

	private static int getBiggestChunkSize(int[][] A, int N) {
		boolean[][] visited = new boolean[N][N];
		int size = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!visited[i][j] && A[i][j] > 0) {
					size = Math.max(size, bfs(A, N, i, j, visited));
				}
			}
		}
		return size;
	}

	private static int bfs(int[][] A, int N, int x, int y, boolean[][] visited) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		visited[x][y] = true;
		int nx, ny, cnt = 0;
		while (!queue.isEmpty()) {
			++cnt;
			Pair p = queue.poll();
			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (A[nx][ny] == 0)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return cnt;
	}

	private static int getTotalSum(int[][] A, int N) {
		int res = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				res += A[i][j];
			}
		}
		return res;
	}

	private static void mapCopy(int[][] from, int[][] to, int x, int y, int l) {
		for (int i = 0; i < l; ++i) {
			for (int j = 0; j < l; ++j) {
				to[x + i][y + j] = from[i][j];
			}
		}
	}

	private static int[][] rotate(int[][] A, int x, int y, int l) {
		int[][] rotated = temp;
		for (int i = 0; i < l; ++i) {
			for (int j = 0; j < l; ++j) {
				rotated[j][l - i - 1] = A[x + i][y + j];
			}
		}
		return rotated;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
