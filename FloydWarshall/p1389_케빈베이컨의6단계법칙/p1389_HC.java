package FloydWarshall.p1389_케빈베이컨의6단계법칙;

import java.io.*;
import java.util.*;

public class p1389_HC {

	private static final int INF = Integer.MAX_VALUE >> 2;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N + 1][N + 1];
		for (int i = 0; i <= N; ++i) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0, A, B; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			graph[A][B] = 1;
			graph[B][A] = 1;
		}

		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		for (int i = 1; i <= N; ++i) {
			int sum = 0;
			for (int j = 1; j <= N; ++j) {
				sum += graph[i][j];
			}
			if (min > sum) {
				min = sum;
				minIdx = i;
			}
		}
		System.out.println(minIdx);
	    br.close();
	}
}
