package DynamicProgramming.p1520_내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1520_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, map[][], dp[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	static int dfs(int x, int y) {
		if (x == M - 1 && y == N - 1) {
			return 1;
		}

		// 이미 방문했다면
		if (dp[x][y] != -1) {
			return dp[x][y];
			
		} else {
			dp[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int nowX = x + dx[i];
				int nowY = y + dy[i];

				if (nowX < 0 || nowY < 0 || nowX >= M || nowY >= N)
					continue;

				if (map[nowX][nowY] < map[x][y])
					dp[x][y] += dfs(nowX, nowY);
			}
		}
		return dp[x][y];
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<M; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		dfs(0, 0);

		System.out.println(dp[0][0]);

		br.close();
	}
}
