package BinarySearch.p4001_미노타우르스미궁;

import java.io.*;
import java.util.*;

/**
 * 누적합 + 이분탐색 + 그래프탐색
 */
public class p4001_HC {

	private static final int LEFT_HAND = 1;
	private static final int RIGHT_HAND = -1;
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	private static int w, h;
	private static boolean[][] isWall;

	public static void main(String[] args) throws Exception {
		input();

		boolean[][] way1 = findWay(1, LEFT_HAND);
		boolean[][] way2 = findWay(0, RIGHT_HAND);
		int[][] sums1 = accumulate(way1);
		int[][] sums2 = accumulate(way2);

		isWall[0][0] = true;
		isWall[h - 1][w - 1] = true;
		int[][] sums3 = accumulate(isWall);

		int[] res = {Integer.MAX_VALUE, -1, -1};
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				int left = 0;
				int right = Math.min(h - y, w - x);
				int mid;
				boolean find = false;
				while (left <= right) {
					mid = (left + right) >> 1;
					if (exist(sums1, mid, x, y) && exist(sums2, mid, x, y)) {
						find = true;
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				if (find && res[0] > right + 1 && !exist(sums3, right + 1, x, y)) {
					res[0] = right + 1;
					res[1] = x + 1;
					res[2] = y + 1;
				}
			}
		}

		if (res[0] == Integer.MAX_VALUE) {
			System.out.println("Impossible");
		} else {
			System.out.println(res[0] + " " + res[1] + " " + res[2]);
		}
	}

	private static boolean exist(int[][] sums, int l, int x, int y) {
		return sums[y + l][x + l] - sums[y + l][x] - sums[y][x + l] + sums[y][x] > 0;
	}

	private static int[][] accumulate(boolean[][] way) {
		int[][] sums = new int[h + 1][w + 1];
		for (int j = 1; j <= w; ++j) {
			for (int i = 1; i <= h; ++i) {
				sums[i][j] += sums[i][j - 1] + (way[i - 1][j - 1] ? 1 : 0);
			}
		}
		for (int i = 1; i <= h; ++i) {
			for (int j = 1; j <= w; ++j) {
				sums[i][j] += sums[i - 1][j];
			}
		}
		return sums;
	}

	private static boolean[][] findWay(int dir, int d) {
		boolean[][] way = new boolean[h][w];
		int y = 0;
		int x = 0;
		int ny, nx;
		while (x != w - 1 || y != h - 1) {
			dir = (dir + d + 4) % 4;
			ny = y + dy[dir];
			nx = x + dx[dir];
			while (nx < 0 || ny < 0 || nx >= w || ny >= h || isWall[ny][nx]) {
				dir = (dir - d + 4) % 4;
				ny = y + dy[dir];
				nx = x + dx[dir];
			}
			y = ny;
			x = nx;
			way[ny][nx] = true;
		}
		return way;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		isWall = new boolean[h][w];

		for (int i = 0; i < h; ++i) {
			String line = br.readLine();
			for (int j = 0; j < w; ++j) {
				isWall[i][j] = line.charAt(j) == '#';
			}
		}
		br.close();
	}
}