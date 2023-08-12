package BFS_DFS.p2468_안전영역;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2468_YK {
	static int N, result, h;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int min = Integer.MAX_VALUE, max = 0;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] < min)
					min = arr[i][j];
				else if (arr[i][j] > max)
					max = arr[i][j];
			}
		}
		
		if (min == max) min -= 1;

		for (int k = min; k <= max; ++k) {
			visited = new boolean[N][N];
			int tmp = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (!visited[i][j] && arr[i][j] > k) {
						tmp++;
						bfs(i, j, k);
					}
				}
			}
			if (tmp > result) {
				result = tmp;
			}
		}

		bw.write(String.valueOf(result));
		br.close();
		bw.flush();
		bw.close();
	}

	public static void bfs(int x, int y, int h) {
		int[][] di = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] now = q.remove();
			for (int i = 0; i < 4; ++i) {
				int nx = now[0] + di[i][0];
				int ny = now[1] + di[i][1];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!visited[nx][ny] && arr[nx][ny] > h) {
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
	}
}
