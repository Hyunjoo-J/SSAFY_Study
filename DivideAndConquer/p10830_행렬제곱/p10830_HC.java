package DivideAndConquer.p10830_행렬제곱;

import java.io.*;
import java.util.*;

public class p10830_HC {

	private static final int MOD = 1000;
	private static int[][] identityMatrix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		identityMatrix = new int[N][N];
		int[][] matrix = new int[N][N];
		for (int i = 0; i < N; ++i) {
			identityMatrix[i][i] = 1;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
			}
		}
		printMatrix(matrixPower(matrix, B), N);
		br.close();
	}

	private static int[][] matrixPower(int[][] a, long b) {
		if (b == 0)
			return identityMatrix;

		int[][] temp = matrixPower(a, b >> 1);
		if ((b & 1) == 1) {
			return matrixMultiplication(matrixMultiplication(temp, temp), a);
		} else {
			return matrixMultiplication(temp, temp);
		}
	}

	private static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
		int n = matrix1.length;
		int[][] multiplied = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				for (int k = 0; k < n; ++k) {
					multiplied[i][j] += matrix1[i][k] * matrix2[k][j];
					multiplied[i][j] %= MOD;
				}
			}
		}
		return multiplied;
	}

	private static void printMatrix(int[][] matrix, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j)
				sb.append(matrix[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

