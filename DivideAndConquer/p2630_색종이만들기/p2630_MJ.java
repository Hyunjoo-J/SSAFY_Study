package DivideAndConquer.p2630_색종이만들기;

import java.util.*;
import java.io.*;

public class p2630_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], cntW = 0, cntB = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divide(0, 0, N);

		System.out.println(cntW);
		System.out.println(cntB);

		br.close();
	}

	private static void divide(int row, int col, int size) {

		if (checkColor(row, col, size)) {
			if (map[row][col] == 0) {
				cntW++;
			} else
				cntB++;
			return;
		}

		int newSize = size / 2;

		divide(row, col, newSize);
		divide(row + newSize, col, newSize);
		divide(row, col + newSize, newSize);
		divide(row + newSize, col + newSize, newSize);
	}

	private static boolean checkColor(int row, int col, int size) {

		int tmp = map[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != tmp)
					return false;
			}
		}
		return true;
	}
}
