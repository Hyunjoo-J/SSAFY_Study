package FloydWarshall.p1719_택배;

import java.io.*;
import java.util.*;

public class p1719_HC {

	private static final int INF = Integer.MAX_VALUE >> 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] graph = new int[n + 1][n + 1];
		int[][] answer = new int[n + 1][n + 1];

		for (int i = 1; i <= n; ++i) {
			Arrays.fill(graph[i], 1, n + 1, INF);
		}

		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
			graph[b][a] = c;
			answer[a][b] = b;
			answer[b][a] = a;
		}

		for (int k = 1; k <= n; ++k) {
			for (int i = 1; i <= n; ++i) {
				for (int j = 1; j <= n; ++j) {
					if (i == j)
						continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						answer[i][j] = answer[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (i == j)
					sb.append("- ");
				else
					sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
