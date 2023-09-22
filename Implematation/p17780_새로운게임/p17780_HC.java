package Implematation.p17780_새로운게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p17780_HC {

	private static class Pair {
		int top, topDir, bottom, bottomDir, size;

		public Pair() {
			invalidate();
		}

		public Pair(int top, int topDir, int bottom, int bottomDir, int size) {
			this.top = top;
			this.topDir = topDir;
			this.bottom = bottom;
			this.bottomDir = bottomDir;
			this.size = size;
		}

		void invalidate() {
			top = -1;
			topDir = -1;
			bottom = -1;
			bottomDir = -1;
			size = 0;
		}

		void reverse() {
			bottomDir ^= 1;
			if (bottom == top) { // ********
				topDir ^= 1;
			}
		}
	}

	private static final int[] dx = {0, 0, -1, 1};
	private static final int[] dy = {1, -1, 0, 0};
	private static int N, K;
	private static int[][] color;
	private static Pair[][] graph;
	private static boolean gameEnd = false;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		color = new int[N][N];
		graph = new Pair[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				color[i][j] = Integer.parseInt(st.nextToken());
				graph[i][j] = new Pair();
			}
		}

		for (int i = 0, r, c, d; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken()) - 1;
			graph[r][c] = new Pair(i, d, i, d, 1);
		}

		int turn = 0;
		for (; !gameEnd && turn <= 1000; ++turn) {
			for (int i = 0; i < K; ++i) {
				int[] pos = findPosition(i);
				if (pos == null)
					continue;
				move(pos[0], pos[1], false);
			}
		}
		System.out.println(turn >= 1000 ? -1 : turn);
	    bw.flush();
	    bw.close();
	    br.close();
	}

	private static void move(int x, int y, boolean flag) {
		int dir = graph[x][y].bottomDir;
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx < 0 || ny < 0 || nx >= N || ny >= N || color[nx][ny] == 2) {
			if (flag)
				return;
			graph[x][y].reverse();
			move(x, y, true);
		} else if (color[nx][ny] == 1) {
			if (graph[nx][ny].size == 0) {
				graph[nx][ny].bottom = graph[x][y].top;
				graph[nx][ny].bottomDir = graph[x][y].topDir;
			}
			graph[nx][ny].top = graph[x][y].bottom;
			graph[nx][ny].topDir = graph[x][y].bottomDir;
			graph[nx][ny].size += graph[x][y].size;
			graph[x][y].invalidate();
			if (graph[nx][ny].size >= 4)
				gameEnd = true;
		} else {
			if (graph[nx][ny].size == 0) {
				graph[nx][ny].bottom = graph[x][y].bottom;
				graph[nx][ny].bottomDir = graph[x][y].bottomDir;
			}
			graph[nx][ny].top = graph[x][y].top;
			graph[nx][ny].topDir = graph[x][y].topDir;
			graph[nx][ny].size += graph[x][y].size;
			graph[x][y].invalidate();
			if (graph[nx][ny].size >= 4)
				gameEnd = true;
		}
	}

	private static int[] findPosition(int idx) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (graph[i][j].bottom == idx)
					return new int[] {i, j};
			}
		}
		return null;
	}
}
