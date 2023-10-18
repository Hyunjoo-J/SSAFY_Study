package PrefixSum.p25682_체스판다시칠하기2;

import java.io.*;
import java.util.*;

public class p25682_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static char board[][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			String tmpS = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = tmpS.charAt(j);
			}
		}
		
		System.out.println(Math.min(minBoard('B'), minBoard('W')));

		br.close();
	}

	private static int minBoard(char color) {
		int prefix[][] = new int[N + 1][M + 1];
		int tmp;
		int cnt = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((i + j) % 2 == 0) {
					tmp = (board[i][j] == color) ? 1 : 0;
				} else {
					tmp = (board[i][j] != color) ? 1 : 0;
				}
				// 2차원 누적합 구하는 방법
				prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j] + tmp;
			}
		}

		for (int i = 1; i <= N - K + 1; i++) {
			for (int j = 1; j <= M - K + 1; j++) {
				// 누적합을 이용하여 부분합을 구하는 방법
				cnt = Math.min(cnt, prefix[i + K - 1][j + K - 1] - prefix[i + K - 1][j - 1] - prefix[i - 1][j + K - 1]
						+ prefix[i - 1][j - 1]);
			}
		}
		
		return cnt;
	}
}
