package Implematation.p19237_어른상어;

import java.io.*;
import java.util.*;

public class p19237_HC {

	private static final int[] dx = {-1, 1, 0, 0};
	private static final int[] dy = {0, 0, -1, 1};

	private static int N, M, k;
	private static int[][] graph;
	private static int[][] graphCache;
	private static int[][][] sharkPriority;
	private static int[] sharkDirection;
	private static Smell[][] sharkSmell;

	public static void main(String[] args) throws Exception {
		inputAndDataInit();

		int time;
		for (time = 1; time <= 1000; ++time) {
			sharkMove(time);
			if (isEndCondition()) {
				break;
			}
		}
		System.out.println(time > 1000 ? -1 : time);
	}

	private static boolean isEndCondition() {
		boolean existsFirstShark = false;
		boolean nonExistsOtherSharks = true;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (graph[i][j] == 1) {
					existsFirstShark = true;
				} else if (graph[i][j] > 1) {
					nonExistsOtherSharks = false;
				}
			}
		}
		return existsFirstShark && nonExistsOtherSharks;
	}

	private static void sharkMove(int time) {
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				if (graph[x][y] == 0)
					continue;

				int sharkNum = graph[x][y];
				int nx, ny, dir = sharkDirection[sharkNum];
				boolean move = false;
				for (int i: sharkPriority[sharkNum][dir]) {
					nx = x + dx[i];
					ny = y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (sharkSmell[nx][ny].isSmellRemains(time))
						continue;

					sharkDirection[sharkNum] = i;
					graphCache[nx][ny] = getHighPriority(graphCache[nx][ny], sharkNum);
					graph[x][y] = 0;
					move = true;
					break;
				}

				if (!move) {
					for (int i: sharkPriority[sharkNum][dir]) {
						nx = x + dx[i];
						ny = y + dy[i];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;
						if (sharkSmell[nx][ny].shark != sharkNum)
							continue;

						sharkDirection[sharkNum] = i;
						graphCache[nx][ny] = getHighPriority(graphCache[nx][ny], sharkNum);
						graph[x][y] = 0;
						move = true;
						break;
					}
				}

				if (!move) {
					graphCache[x][y] = graph[x][y];
					graph[x][y] = 0;
				}
			}
		}
		int[][] temp = graph;
		graph = graphCache;
		graphCache = temp;

		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < N; ++y) {
				if (graph[x][y] > 0)
					sharkSmell[x][y] = new Smell(graph[x][y], time);
			}
		}
	}

	private static int getHighPriority(int shark1, int shark2) {
		if (shark1 == 0 || shark2 == 0)
			return Math.max(shark1, shark2);
		return Math.min(shark1, shark2);
	}

	private static void inputAndDataInit() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new int[N][N];
		graphCache = new int[N][N];
		sharkDirection = new int[M + 1];
		sharkPriority = new int[M + 1][4][4];
		sharkSmell = new Smell[N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				sharkSmell[i][j] = new Smell(graph[i][j], 0);
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; ++i) {
			sharkDirection[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int i = 1; i <= M; ++i) {
			for (int j = 0; j < 4; ++j) {
				st = new StringTokenizer(br.readLine());
				for (int p = 0; p < 4; ++p) {
					sharkPriority[i][j][p] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		br.close();
	}

	private static class Smell {
		int shark, time;

		Smell(int shark, int time) {
			this.shark = shark;
			this.time = time;
		}

		boolean isSmellRemains(int time) {
			if (shark == 0)
				return false;
			return time - this.time <= k;
		}
	}
}
