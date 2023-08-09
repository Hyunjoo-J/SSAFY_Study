import java.io.*;
import java.util.*;

public class p19236_청소년상어_HJ {
	public static class Fish {
		private int size;
		private int dir;

		public Fish(int size, int dir) {
			this.size = size;
			this.dir = dir;
		}
	}
	public static class Find {
		private int x;
		private int y;
		private int dir;
		
		public Find(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}

	static Fish[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new Fish[4][4];
		int size, dir;
		for (int i = 0; i < 4; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; ++j) {
				size = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				map[i][j] = new Fish(size, dir - 1);
			}
		}
		Fish shark = new Fish(map[0][0].size, map[0][0].dir);
		map[0][0] = null;
		int max = move_s(0, 0, shark.dir, shark.size, map);
		System.out.println(max);
	}
	private static int move_s(int sx, int sy, int dir, int sum, Fish[][] now) {
		int tsum = sum;
		Fish[][] next = move_f(sx, sy, now);

		for (int i = 1; i < 4; i++) {
			int nx = sx + dx[dir] * i;
			int ny = sy + dy[dir] * i;
			if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
				if (next[nx][ny] != null) {
					Fish tmp = next[nx][ny];
					next[nx][ny] = null;
					tsum = Math.max(tsum, move_s(nx, ny, tmp.dir, sum + tmp.size, next));
					next[nx][ny] = tmp;
				}
			} else {
				return tsum;
			}
		}
		return tsum;
	}

	private static Fish[][] move_f(int sx, int sy, Fish[][] map) {
		Fish[][] next = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != null) {
					next[i][j] = new Fish(map[i][j].size, map[i][j].dir);
				}
			}
		}

		for (int idx = 1; idx <= 16; idx++) {
			Find num = find(idx, next);
			if (num != null) {
				for (int change = 0; change < 8; change++) {
					int nx = num.x + dx[(num.dir + change) % 8];
					int ny = num.y + dy[(num.dir + change) % 8];

					if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && (nx != sx || ny != sy)) {
						if (next[nx][ny] != null) {
							Fish temp = next[nx][ny];
							next[nx][ny] = new Fish(next[num.x][num.y].size, (num.dir + change) % 8);
							next[num.x][num.y] = new Fish(temp.size, temp.dir);
						} else {
							next[nx][ny] = new Fish(next[num.x][num.y].size, (num.dir + change) % 8);
							next[num.x][num.y] = null;
						}
						break;
					}
				}
			}
		}
		return next;
	}

	private static Find find(int idx, Fish[][] map) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != null && map[i][j].size == idx) {
					return new Find(i, j, map[i][j].dir);
				}
			}
		}
		return null;
	}
}

