package Implematation.p12100_2048Easy;

import java.io.*;
import java.util.*;

public class p12100_HJ {
	static int N, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		game(0, map);
		System.out.println(max);
	}

	private static void game(int dep, int[][] copy) {
		if (dep == 5) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					max = Math.max(max, copy[i][j]);
				}
			}
			return;
		}
		for (int i = 0; i < 4; ++i) {
			int[][] tmp = new int[N][N];
			for (int j = 0; j < N; ++j) {
				tmp[j] = copy[j].clone();
			}
			tmp = move(i, tmp);
			game(dep + 1, tmp);
		}
		
	}

	private static int[][] move(int dir, int[][] tmp) {
		switch (dir) {
		case 0: // 위로
			for (int i = 0; i < N; ++i) {
				int idx = 0;
				int num = 0;
				for (int j = 0; j < N; ++j) {
					if (tmp[j][i] == 0)
						continue;
					if (tmp[j][i] == num) {
						tmp[idx - 1][i] = num * 2;
						num = 0;
						tmp[j][i] = 0;
					} else {
						num = tmp[j][i];
						tmp[j][i] = 0;
						tmp[idx][i] = num;
						++idx;
					}
				}
			}
			break;
		case 1: // 왼쪽으로
			for (int i = 0; i < N; ++i) {
				int idx = 0;
				int num = 0;
				for (int j = 0; j < N; ++j) {
					if (tmp[i][j] == 0)
						continue;
					if (tmp[i][j] == num) {
						tmp[i][idx - 1] = num * 2;
						num = 0;
						tmp[i][j] = 0;
					} else {
						num = tmp[i][j];
						tmp[i][j] = 0;
						tmp[i][idx] = num;
						++idx;
					}
				}
			}
			break;
		case 2: // 아래로
			for (int i = 0; i < N; ++i) {
				int idx = N - 1;
				int num = 0;
				for (int j = N - 1; j >= 0; --j) {
					if (tmp[j][i] == 0)
						continue;
					if (tmp[j][i] == num) {
						tmp[idx + 1][i] = num * 2;
						num = 0;
						tmp[j][i] = 0;
					} else {
						num = tmp[j][i];
						tmp[j][i] = 0;
						tmp[idx][i] = num;
						--idx;
					}
				}
			}
			break;
		case 3: // 오른쪽으로
			for (int i = 0; i < N; ++i) {
				int idx = N - 1;
				int num = 0;
				for (int j = N - 1; j >= 0; --j) {
					if (tmp[i][j] == 0)
						continue;
					if (tmp[i][j] == num) {
						tmp[i][idx + 1] = num * 2;
						num = 0;
						tmp[i][j] = 0;
					} else {
						num = tmp[i][j];
						tmp[i][j] = 0;
						tmp[i][idx] = num;
						--idx;
					}
				}
			}
			break;
		}
		return tmp;
	}
}