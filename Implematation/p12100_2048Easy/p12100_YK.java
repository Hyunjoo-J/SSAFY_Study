package Implematation.p12100_2048Easy;

import java.io.*;
import java.util.*;

public class p12100_YK {

    static int N, result;
    static int[] order;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        order = new int[5];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                result = Math.max(map[i][j], result);
            }
        }

        perm(0);
        System.out.println(result);
    }

    private static void perm(int cnt) {
        if (cnt == 5) {
            // 방향 별 검증하기
            int[][] copy = new int[N][N];
            for (int i = 0; i < N; ++i) {
                copy[i] = Arrays.copyOf(map[i], N);
            }

            for (int o : order) { // 0 : 우 / 1 : 상 / 2 : 좌 / 3 : 하
                // 돌리기
                rotate(copy, o);
                // 이동하기
                copy = move(copy); // move는 항상 우(->) 방향으로
                // 다시 원복
                rotate(copy, -o);
            }

            return;
        }

        for (int i = 0; i < 4; ++i) {
            order[cnt] = i;
            perm(cnt + 1);
        }
    }

    private static void rotate(int[][] m, int o) {
        int[][] tmp = new int[N][N];

        if (o < 0) o += 4;

        for (int k = 0; k < o; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    tmp[i][j] = m[j][N - i - 1];
                }
            }
            for (int i = 0; i < N; ++i) {
                m[i] = Arrays.copyOf(tmp[i], N);
            }
        }
    }

    private static int[][] move(int[][] m) {
        int[][] ans = new int[N][N];

        int y, poll1, poll2;
        for (int i = 0; i < N; ++i) {
            Queue<Integer> q = new LinkedList<>();
            for (int j = N - 1; j >= 0; --j) {
                if (m[i][j] != 0) q.offer(m[i][j]);
            }

            y = N - 1;
            if (q.isEmpty()) continue;
            if (q.size() == 1) {
                ans[i][y] = q.poll();
                continue;
            }
            poll1 = q.poll();
            while (!q.isEmpty()) {
                poll2 = q.poll();
                if (poll1 == poll2) {
                    ans[i][y] = poll1 << 1;
                    if (!q.isEmpty()) poll1 = q.poll();
                    else poll1 = 0;
                }
                else {
                    ans[i][y] = poll1;
                    poll1 = poll2;
                }

                result = Math.max(result, ans[i][y]);
                --y;
            }
            ans[i][y] = poll1;
        }

        return ans;
    }

}