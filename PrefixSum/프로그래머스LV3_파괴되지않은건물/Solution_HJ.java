package PrefixSum.프로그래머스LV3_파괴되지않은건물;

class Solution {
	public int solution(int[][] board, int[][] skill) {
		int N = board.length;
		int M = board[0].length;
		int[][] sum = new int[N + 1][M + 1];
		int r1, c1, r2, c2, degree;
		for(int[] s : skill){
			r1 = s[1];
			c1 = s[2];
			r2 = s[3];
			c2 = s[4];
			degree = s[5];
			if(s[0] == 1){
				sum[r1][c1] -= degree;
				sum[r2 + 1][c1] += degree;
				sum[r1][c2 + 1] += degree;
				sum[r2 + 1][c2 + 1] -= degree;
			}else{
				sum[r1][c1] += degree;
				sum[r2 + 1][c1] -= degree;
				sum[r1][c2 + 1] -= degree;
				sum[r2 + 1][c2 + 1] += degree;
			}
		}
		for(int i = 0; i < N; ++i){
			for(int j = 1; j < M; ++j){
				sum[i][j] += sum[i][j - 1];
			}
		}

		for(int i = 0; i < M; ++i){
			for(int j = 1; j < N; ++j){
				sum[j][i] += sum[j - 1][i];
			}
		}

		int ans = 0;
		for(int i = 0; i < N; ++i){
			for(int j = 0; j < M; ++j){
				if(board[i][j] + sum[i][j] > 0)
					++ans;
			}
		}
		return ans;
	}
}