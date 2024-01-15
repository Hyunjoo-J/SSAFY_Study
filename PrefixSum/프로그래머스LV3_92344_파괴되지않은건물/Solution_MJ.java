package Programmers.LV3_92344_파괴되지않은건물;

class Solution_MJ {

	static int N, M, cnt, skillCnt;
	static int map[][];

	public int solution(int[][] board, int[][] skill) {
		int answer = 0;
		N = board.length;
		M = board[0].length;
		skillCnt = skill.length;
		map = new int[N + 2][M + 2];

		for (int s = 0; s < skillCnt; s++) {
			int type = skill[s][0];
			int r1 = skill[s][1];
			int c1 = skill[s][2];
			int r2 = skill[s][3];
			int c2 = skill[s][4];
			int degree = skill[s][5];

			if (type == 1) {
				map[r1][c1] -= degree;
				map[r1][c2 + 1] += degree;
				map[r2 + 1][c1] += degree;
				map[r2 + 1][c2 + 1] -= degree;
			} else {
				map[r1][c1] += degree;
				map[r1][c2 + 1] -= degree;
				map[r2 + 1][c1] -= degree;
				map[r2 + 1][c2 + 1] += degree;
			}
		}

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 1; j++) {
				map[i][j + 1] += map[i][j];
			}
		}

		for (int j = 0; j < M + 2; j++) {
			for (int i = 0; i < N + 1; i++) {
				map[i + 1][j] += map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] + map[i][j] > 0)
					answer++;
			}
		}

		return answer;
	}
}
