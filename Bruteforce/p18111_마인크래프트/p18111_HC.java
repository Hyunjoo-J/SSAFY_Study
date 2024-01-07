package Bruteforce.p18111_마인크래프트;

import java.io.*;
import java.util.*;

public class p18111_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N][M];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = Integer.MAX_VALUE;
		int bestH = -1;
		for (int h = 0; h < 257; ++h) {
			int[] res = test(graph, N, M, h);
			if (B >= res[1] && time >= res[0]) {
				time = res[0];
				bestH = h;
			}
		}
		System.out.println(time + " " + bestH);
		br.close();
	}

	/**
	 * @return time, used block cnt
	 */
	private static int[] test(int[][] graph, int n, int m, int h) {
		int time = 0;
		int usedBlockCnt = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (graph[i][j] > h) {
					time += 2 * (graph[i][j] - h);
					usedBlockCnt -= graph[i][j] - h;
				} else {
					time += h - graph[i][j];
					usedBlockCnt += h - graph[i][j];
				}
			}
		}
		return new int[] {time, usedBlockCnt};
	}
}
