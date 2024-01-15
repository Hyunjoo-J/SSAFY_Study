package Programmers.LV3_92344_파괴되지않은건물;

class Solution_YK {
   public int solution(int[][] board, int[][] skill) {
       int answer = 0;

       int n = board.length;
       int m = board[0].length;

       int[][] map = new int[n + 2][m + 2];

       int r1, c1, r2, c2, degree;
       for (int[] sk : skill) {
           r1 = sk[1] + 1;
           c1 = sk[2] + 1;
           r2 = sk[3] + 1;
           c2 = sk[4] + 1;
           degree = sk[0] == 1 ? -sk[5] : sk[5];

           map[r1][c1] += degree;
           map[r1][c2 + 1] -= degree;
           map[r2 + 1][c1] -= degree;
           map[r2 + 1][c2 + 1] += degree;
       }

       int sum = 0;
       for (int i = 0; i < n + 2; ++i) {
           for (int j = 0; j < m + 2; ++j) {
               sum += map[i][j];
               map[i][j] = sum;
           }
       }

       sum = 0;
       for (int i = 0; i < m + 2; ++i) {
           for (int j = 0; j < n + 2; ++j) {
               sum += map[j][i];
               map[j][i] = sum;
           }
       }

       for (int i = 0; i < n; ++i) {
           for (int j = 0; j < m; ++j) {
               if (map[i + 1][j + 1] + board[i][j] > 0)
                   ++answer;
           }
       }

       return answer;
   }
}
