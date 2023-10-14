package PrefixSum.p25682_체스판다시칠하기2;

import java.io.*;
import java.util.*;

public class p25682_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N + 1][M + 1];
		for (int i = 1; i <= N; ++i) {
			String line = br.readLine();
			for (int j = 1; j <= M; ++j) {
				graph[i][j] = line.charAt(j - 1) == 'B' ? 1 : 0;
			}
		}

		int[][] psum1 = new int[N + 1][M + 1];
		int[][] psum2 = new int[N + 1][M + 1];

		for (int i = 1, color = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j, color = 1 - color) {
				if (graph[i][j] != color)
					psum1[i][j] = 1;
				if (graph[i][j] != 1 - color)
					psum2[i][j] = 1;
			}
			if ((M & 1) == 0)
				color = 1 - color;
		}

		// accumulate
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				psum1[i][j] += psum1[i][j - 1];
				psum2[i][j] += psum2[i][j - 1];
			}
		}

		for (int j = 1; j <= M; ++j) {
			for (int i = 1; i <= N; ++i) {
				psum1[i][j] += psum1[i - 1][j];
				psum2[i][j] += psum2[i - 1][j];
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= N - K; ++i) {
			for (int j = 0; j <= M - K; ++j) {
				answer = min(answer,
					psum1[i + K][j + K] - psum1[i + K][j] - psum1[i][j + K] + psum1[i][j],
					psum2[i + K][j + K] - psum2[i + K][j] - psum2[i][j + K] + psum2[i][j]);
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static int min(int v1, int v2, int v3) {
		if (v1 > v2) {
			return Math.min(v2, v3);
		} else {
			return Math.min(v1, v3);
		}
	}
}
