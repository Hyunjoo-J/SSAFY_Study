package PrefixSum.p25682_체스판다시칠하기2;

import java.util.*;
import java.io.*;

public class p25682_HJ {
	static int N, M, K;
	static char[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i = 0; i < N; ++i)
			map[i] = br.readLine().toCharArray();
		System.out.println(Math.min(cal('B'), cal('W')));
	}

	private static int cal(char color){
		int[][] sum = new int[N + 1][M + 1];
		int value;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if((i + j) % 2 == 0) {
					value = map[i][j] != color? 1 : 0;
				}else {
					value = map[i][j] == color? 1 : 0;
				}
				sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + value;
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 1; i <= N - K + 1; i++) {
			for(int j = 1; j <= M - K + 1; j++) {
				ans = Math.min(ans, sum[i + K - 1][j + K - 1] - sum[i + K - 1][j - 1] - sum[i - 1][j + K - 1] + sum[i - 1][j - 1]);
			}
		}
		return ans;
	}
}
