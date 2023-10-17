package DivideAndConquer.p10830_행렬제곱;

import java.io.*;
import java.util.*;

public class p10830_YK {
	static final int MOD = 1000;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken()); 
		int[][] matrix = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] result = pow(matrix, B);
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				sb.append(result[i][j] % MOD).append(" ");
			} sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

	private static int[][] pow(int[][] m, long b) {
		if (b == 1) return m;

		if (b % 2 == 0) {
			int[][] tmp = pow(m, b / 2);
			return mul(tmp, tmp);
		}
		return mul(m, pow(m, b - 1));
	}

	private static int[][] mul(int[][] m1, int[][] m2) {
		int[][] ans = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < N; ++k) {
					ans[i][j] += (m1[i][k] * m2[k][j]) % MOD;
				}
				ans[i][j] %= MOD;
			}
		}
		
		return ans;
	}
}
