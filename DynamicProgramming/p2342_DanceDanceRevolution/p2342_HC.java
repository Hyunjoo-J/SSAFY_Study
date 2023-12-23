package DynamicProgramming.p2342_DanceDanceRevolution;

import java.io.*;
import java.util.*;

public class p2342_HC {

    private static int N;
    private static int[] seq;
    private static int[][][] mem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = st.countTokens() - 1;
        seq = new int[N];
        mem = new int[N][5][5];
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(mem[i][j], -1);
            }
        }
        System.out.println(dfs(0, 0, 0));
        br.close();
    }

    private static int dfs(int left, int right, int depth) {
        if (depth == N)
            return 0;
        if (mem[depth][left][right] != -1)
            return mem[depth][left][right];

        mem[depth][left][right] = 0;
        int leftMove = move(left, seq[depth]) + dfs(seq[depth], right, depth + 1);
        int rightMove = move(right, seq[depth]) + dfs(left, seq[depth], depth + 1);
        return mem[depth][left][right] = Math.min(leftMove, rightMove);
    }

    private static int move(int now, int next) {
        if (now == next)
            return 1;
        if (now == 0)
            return 2;
        if (Math.abs(next - now) == 2)
            return 4;
        return 3;
    }
}
