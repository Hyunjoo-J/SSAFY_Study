package DivideAndConquer.p10830_행렬제곱;

import java.util.*;
import java.io.*;

public class p10830_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], ans[][];
	static int[][] identityMatrix;
	static long B;
	private static final int MOD = 1000;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());

		map = new int[N][N];
		identityMatrix = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			identityMatrix[i][i] = 1;
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] ans = pow(map, B);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] matrixMultiplication(int[][] a, int[][] b) {
		int[][] multiplied = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					multiplied[i][j] += a[i][k] * b[k][j];
					multiplied[i][j] %= MOD;
				}
			}
		}
		return multiplied;
	}

	private static int[][] pow(int[][] a, long b) {
		if (b == 0) {
			return identityMatrix;
		}

		int[][] temp = pow(a, b / 2);
		if (b % 2 == 1) {
//          return ((temp * temp) % MOD * a) % MOD;
			return matrixMultiplication(matrixMultiplication(temp, temp), a);
		} else {
			return matrixMultiplication(temp, temp);
		}
	}
}
