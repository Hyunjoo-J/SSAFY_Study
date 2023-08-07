package Bruteforce.p15683_감시;

import java.io.*;
import java.util.*;

public class p15683_HC {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] kind = {0, 4, 2, 4, 4, 1};
    private static final int[][][] cctvDir = {
            { },
            { {0}, {1}, {2}, {3} },
            { {0, 1}, {2, 3} },
            { {0, 2}, {0, 3}, {1, 2}, {1, 3} },
            { {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3} },
            { {0, 1, 2, 3} }
    };

    private static int N, M, cCnt = 0;
    private static int answer = Integer.MAX_VALUE;
    private static int[][] graph;
    private static int[][] temp;
    private static int[] cctv = new int[8];
    private static Pair[] cctvPos = new Pair[8];
    private static int[] sel = new int[8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        temp = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (0 < graph[i][j] && graph[i][j] < 6) {
                    cctv[cCnt] = graph[i][j];
                    cctvPos[cCnt] = new Pair(i, j);
                    ++cCnt;
                }
            }
        }

        dfs(0);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int idx) {
        if (idx == cCnt) {
            answer = Math.min(answer, simul());
            return;
        }

        int k = kind[cctv[idx]];
        for (int i = 0; i < k; ++i) {
            sel[idx] = i;
            dfs(idx + 1);
        }
    }

    private static int simul() {
        deepCopy(graph, temp);

        int cn, x, y, nx, ny;
        for (int i = 0; i < cCnt; ++i) {
            cn = cctv[i];
            x = cctvPos[i].x;
            y = cctvPos[i].y;
            for (int dir: cctvDir[cn][sel[i]]) {
                nx = x;
                ny = y;
                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        break;
                    if (temp[nx][ny] == 6)
                        break;
                    if (0 < temp[nx][ny] && temp[nx][ny] < 6)
                        continue;
                    temp[nx][ny] = -1;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (temp[i][j] == 0)
                    ++cnt;
            }
        }
        return cnt;
    }

    private static void deepCopy(int[][] from, int[][] to) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                to[i][j] = from[i][j];
            }
        }
    }
}