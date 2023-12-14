package DivideAndConquer.p11444_피보나치수6;

import java.io.*;

public class p11444_HC {

	private static final int[][] IDENTITY_MATRIX = {{1, 0}, {0, 1}};
	private static final int[][] BASE = {{1, 1}, {1, 0}};
	private static final int P = 1_000_000_007;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		System.out.println(matrixPower(BASE, n - 1)[0][0]);
		br.close();
	}

	private static int[][] matrixPower(int[][] matrix, long p) {
		if (p == 0) {
			return IDENTITY_MATRIX;
		}

		int[][] temp = matrixPower(matrix, p >> 1);
		if ((p & 1) == 1) {
			return matrixMultiplication(matrixMultiplication(temp, temp), matrix);
		} else {
			return matrixMultiplication(temp, temp);
		}
	}

	private static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
		int n = matrix1.length;
		int m = matrix1[0].length;
		int r = matrix2[1].length;
		int[][] res = new int[n][r];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < r; ++j) {
				int temp = 0;
				for (int k = 0; k < m; ++k) {
					temp += ((long) matrix1[i][k] * (long) matrix2[k][j]) % P;
					temp %= P;
				}
				res[i][j] = temp;
			}
		}
		return res;
	}
}
