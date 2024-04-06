package Bruteforce.프로그래머스LV2_81302_거리두기확인하기;

class Solution_HC {

	private static final int[] dr = {1, -1, 0, 0, 2, -2, 0, 0, 1, -1, 1, -1};
	private static final int[] dc = {0, 0, 1, -1, 0, 0, 2, -2, 1, 1, -1, -1};

	public int[] solution(String[][] places) {
		int[] answer = {0, 0, 0, 0, 0};
		int index = 0;
		for (String[] place: places) {
			answer[index++] = keepWell(place) ? 1 : 0;
		}
		return answer;
	}

	private static boolean keepWell(String[] place) {
		char[][] matrix = convertStringMapToMatrix(place);
		for (int r = 0; r < 5; ++r) {
			for (int c = 0; c < 5; ++c) {
				if (matrix[r][c] == 'O' || matrix[r][c] == 'X')
					continue;
				for (int i = 0; i < 12; ++i) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5)
						continue;
					if (matrix[nr][nc] != 'P')
						continue;
					if ((i >> 2) == 1 && matrix[r + (dr[i] >> 1)][c + (dc[i] >> 1)] == 'X')
						continue;
					if ((i >> 2) == 2 && matrix[nr][c] == 'X' && matrix[r][nc] == 'X')
						continue;
					return false;
				}
			}
		}
		return true;
	}

	private static char[][] convertStringMapToMatrix(String[] place) {
		char[][] matrix = new char[5][5];
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				matrix[i][j] = place[i].charAt(j);
			}
		}
		return matrix;
	}
}
