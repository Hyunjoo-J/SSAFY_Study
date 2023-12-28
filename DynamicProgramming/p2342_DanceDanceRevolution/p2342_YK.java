package DynamicProgramming.p2342_DanceDanceRevolution;

import java.io.*;
import java.util.*;

public class p2342_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = st.countTokens();
        int[] order = new int[N];
        // 순서, 왼발, 오른발
        int[][][] efforts = new int[N][5][5];

        for (int i = 0; i < N; ++i) {
            order[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(efforts[i][j], Integer.MAX_VALUE >> 1);
            }
        }

        int[][] move = {{0, 2, 2, 2, 2}, {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};
        efforts[0][order[0]][0] = 2;
        efforts[0][0][order[0]] = 2;

        for (int i = 1; i < N; ++i) {
            int now = order[i];
            for (int j = 0; j < 5; ++j) { // 왼
                for (int k = 0; k < 5; ++k) { // 오
                    if (j != now) { // 왼발이 가려는 쪽에 없다면, 오른발이 갈 수 있다.
                        efforts[i][j][now] = Math.min(efforts[i][j][now], efforts[i - 1][j][k] + move[k][now]);
                    }
                    if (k != now) { // 오른발이 가려는 쪽에 없다면, 왼발이 갈 수 있다.
                        efforts[i][now][k] = Math.min(efforts[i][now][k], efforts[i - 1][j][k] + move[j][now]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                result = Math.min(result, efforts[N - 1][i][j]);
            }
        }

        System.out.println(result);
        br.close();
    }
}
