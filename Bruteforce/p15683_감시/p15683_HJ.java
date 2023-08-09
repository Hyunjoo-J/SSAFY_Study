import java.util.*;
import java.io.*;

class p15683_HJ {
	public static class Camera {
		public int x;
		public int y;
		public int num;

		public Camera(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static int n, m;
	static int[][] map;
	static int[][] blind;
	static Camera[] cctv;
	static int min, cnt;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][][] mode = {{{0}}, {{0}, {1}, {2}, {3}}, {{2, 3}, {0, 1}},
			{{0, 3}, {1, 3}, {1, 2}, {0, 2}},
			{{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
			{{0, 1, 2, 3}}};

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		blind = new int[n][m];
		cctv = new Camera[8];
		cnt = 0;
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctv[cnt++] = new Camera(i, j, map[i][j]);
				}
			}
		}
		min = n * m;
		comb(0, cnt, map);
		System.out.println(min);
	}

	private static void comb(int dep, int r, int[][] map) {
		if (dep == r) {
			min = Math.min(min, calzero(map));
			return;
		}
		int num = cctv[dep].num;
		int x = cctv[dep].x;
		int y = cctv[dep].y;
		for (int i = 0; i < mode[num].length; ++i) {
			int[][] copy = new int[n][m];
			for (int j = 0; j < n; ++j) {
				copy[j] = map[j].clone();
			}
			for (int j = 0; j < mode[num][i].length; ++j) {
				int dir = mode[num][i][j];
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				while (true) {
					if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
						break;
					}
					if (map[nx][ny] == 6)
						break;
					copy[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
			}
			comb(dep + 1, r, copy);
		}

	}

	private static int calzero(int[][] map) {
		int res = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (map[i][j] == 0) {
					++res;
				}
			}
		}
		return res;
	}
}
