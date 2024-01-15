package Programmers.LV3_92344_파괴되지않은건물;

class Solution_HC {

	public int solution(int[][] board, int[][] skill) {
		int n = board.length;
		int m = board[0].length;
		int[][] checker = new int[n + 1][m + 1];
		int r1, c1, r2, c2, degree;
		for (int[] s: skill) {
			r1 = s[1]; c1 = s[2];
			r2 = s[3]; c2 = s[4]; degree = s[5];
			if (s[0] == 1) {
				checker[r1][c1] -= degree;
				checker[r2 + 1][c1] += degree;
				checker[r1][c2 + 1] += degree;
				checker[r2 + 1][c2 + 1] -= degree;
			} else {
				checker[r1][c1] += degree;
				checker[r2 + 1][c1] -= degree;
				checker[r1][c2 + 1] -= degree;
				checker[r2 + 1][c2 + 1] += degree;
			}
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < m; ++j) {
				checker[i][j] += checker[i][j - 1];
			}
		}

		for (int j = 0; j < m; ++j) {
			for (int i = 1; i < n; ++i) {
				checker[i][j] += checker[i - 1][j];
			}
		}

		int answer = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (board[i][j] + checker[i][j] > 0)
					++answer;
			}
		}
		return answer;
	}
}
