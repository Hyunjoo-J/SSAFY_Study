package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p2644_YK {
	static int N, M, R;
	static int A, B;
	static boolean[][] arr;
	static boolean[] visited;

	public static void dfs(int now, int count) {
		if (now == B) {
			if (R == -1 || R > count) {
				R = count;
			}
			return;
		}

		for (int i = 0; i < N + 1; ++i) {
			if (!visited[i] && arr[now][i]) {
				visited[i] = true;
				dfs(i, count + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		arr = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		visited[0] = true;

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = true;
			arr[y][x] = true;
		}

		R = -1;
		dfs(A, 0);
		bw.write(String.valueOf(R));
		
		br.close();
		bw.flush();
		bw.close();
	}
}