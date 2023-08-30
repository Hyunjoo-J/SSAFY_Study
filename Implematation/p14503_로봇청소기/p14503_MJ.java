package Implematation.p14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14503_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], robot[], ans = 0;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	private static boolean find(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nowX = x + dx[i];
			int nowY = y + dy[i];

			if (map[nowX][nowY] == 0) {
				cnt++;
				break;
			}
		}

		if (cnt == 0)
			return false;
		else
			return true;
	}
 
	private static void dfs(int x, int y, int dir) {

		if (map[x][y] == 0) {
			map[x][y] = 2;
			ans++;
		}

		if (find(x, y)) {
			int nowDir = (dir + 3) % 4;
			int nowX = x + dx[nowDir];
			int nowY = y + dy[nowDir];

			if (map[nowX][nowY] == 0)
				dfs(nowX, nowY, nowDir);
			else
				dfs(x, y, nowDir);
		} else {
			int nowDir = (dir + 2) % 4;
			int nowX = x + dx[nowDir];
			int nowY = y + dy[nowDir];

			if (map[nowX][nowY] != 1) {
				dfs(nowX, nowY, dir);
			} else {
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		robot = new int[3];

		st = new StringTokenizer(br.readLine());
		robot[0] = Integer.parseInt(st.nextToken());
		robot[1] = Integer.parseInt(st.nextToken());
		robot[2] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(robot[0], robot[1], robot[2]);
        
		System.out.println(ans);

		br.close();
	}
}
