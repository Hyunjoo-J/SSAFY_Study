package DivideAndConquer.p2630_색종이만들기;

import java.io.*;
import java.util.*;

public class p2630_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] res = divideAndConquer(A, 0, 0, N);
		System.out.println(res[0] + "\n" + res[1]);
	    br.close();
	}

	private static int[] divideAndConquer(int[][] A, int x, int y, int l) {
		int blue = 0;
		for (int i = x; i < x + l; ++i) {
			for (int j = y; j < y + l; ++j) {
				blue += A[i][j];
			}
		}

		if (blue == l * l) {
			return new int[]{0, 1};
		} else if (blue == 0) {
			return new int[]{1, 0};
		} else {
			int l2 = l >> 1;
			int[] res1 = divideAndConquer(A, x, y, l2);
			int[] res2 = divideAndConquer(A, x + l2, y, l2);
			int[] res3 = divideAndConquer(A, x, y + l2, l2);
			int[] res4 = divideAndConquer(A, x + l2, y + l2, l2);
			return new int[]{
				res1[0] + res2[0] + res3[0] + res4[0],
				res1[1] + res2[1] + res3[1] + res4[1]
			};
		}
	}
}
