import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, map[][], cnt, sum[], tmpSum;
	static boolean visited[][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];
		sum = new int[M * N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int leftX = Integer.parseInt(st.nextToken());
			int leftY = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			int rightY = Integer.parseInt(st.nextToken());

			for (int x = M - rightY; x < M - leftY; x++) {
				for (int y = leftX; y < rightX; y++) {
					map[x][y] = 1;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 0) {
					dfs(i, j);
					sum[cnt] = tmpSum;
					cnt++;
					tmpSum = 0;
				}
			}
		}

		sum = Arrays.copyOf(sum, cnt);
		Arrays.sort(sum);
		
		System.out.println(cnt);
		for (int i = 0; i < cnt; i++) {
			System.out.print(sum[i] + " ");
		}
		
		br.close();
	}

	static void dfs(int x, int y) {

		if (map[x][y] == 1)
			return;

		visited[x][y] = true;
		tmpSum++;

		for (int i = 0; i < 4; i++) {
			int nowX = x + dx[i];
			int nowY = y + dy[i];

			if (nowX >= 0 && nowY >= 0 && nowX < M && nowY < N) {
				if (map[nowX][nowY] == 0) {
					if (!visited[nowX][nowY]) {
						dfs(nowX, nowY);
					}
				}
			}
		}
	}
}
