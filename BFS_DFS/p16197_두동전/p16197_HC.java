package BFS_DFS.p16197_두동전;

import java.io.*;
import java.util.*;

public class p16197_HC {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cIdx = 0;
		int cx1 = -1, cy1 = -1, cx2 = -1, cy2 = -1;

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			for (int j = 0; j < M; ++j) {
				if (input.charAt(j) == 'o') {
					if (cIdx++ == 0) {
						cx1 = i; cy1 = j;
					} else {
						cx2 = i; cy2 = j;
					}
				}
				graph[i][j] = input.charAt(j) == '#' ? 1 : 0;
			}
		}

		boolean[][][][] visited = new boolean[N][M][N][M];
		Queue<Pair> queue = new ArrayDeque<>();
		visited[cx1][cy1][cx2][cy2] = true;
		queue.add(new Pair(cx1, cy1, cx2, cy2));

		boolean isEnd = false;
		int answer = 0;
		while (!queue.isEmpty() && answer < 10) {
			++answer;
			int queueSize = queue.size();
			for (int q = 0; q < queueSize; ++q) {
				Pair now = queue.poll();

				int nx1, ny1, nx2, ny2;
				for (int i = 0; i < 4; ++i) {
					nx1 = now.cx1 + dx[i];
					ny1 = now.cy1 + dy[i];
					nx2 = now.cx2 + dx[i];
					ny2 = now.cy2 + dy[i];

					if (isRange(nx1, ny1, N, M) && graph[nx1][ny1] == 1) {
						nx1 -= dx[i];
						ny1 -= dy[i];
					}
					if (isRange(nx2, ny2, N, M) && graph[nx2][ny2] == 1) {
						nx2 -= dx[i];
						ny2 -= dy[i];
					}

					boolean b1 = isRange(nx1, ny1, N, M);
					boolean b2 = isRange(nx2, ny2, N, M);
					if (b1 && b2) {
						if (!visited[nx1][ny1][nx2][ny2]) {
							queue.add(new Pair(nx1, ny1, nx2, ny2));
							visited[nx1][ny1][nx2][ny2] = true;
						}
					} else if (b1 || b2) {
						q = 123456789;
						queue.clear();
						isEnd = true;
						break;
					}
				}
			}
		}
		System.out.println(isEnd ? answer : -1);
	    br.close();
	}

	private static boolean isRange(int x, int y, int N, int M) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	private static class Pair {
		int cx1, cy1, cx2, cy2;

		public Pair(int cx1, int cy1, int cx2, int cy2) {
			this.cx1 = cx1;
			this.cy1 = cy1;
			this.cx2 = cx2;
			this.cy2 = cy2;
		}
	}
}
