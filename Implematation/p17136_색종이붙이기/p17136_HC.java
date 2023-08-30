package Implematation.p17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class p17136_HC {

    private static final int INF = Integer.MAX_VALUE;
    private static int[] paper = {0, 5, 5, 5, 5, 5};
    private static boolean[][] graph = new boolean[10][10];
    private static int answer = INF;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; ++j) {
                graph[i][j] = st.nextToken().charAt(0) == '1';
            }
        }
        dfs(0, 0);
        System.out.println(answer == INF ? -1 : answer);
        br.close();
    }


    private static void dfs(int idx, int depth) {
        if (depth >= answer)
            return;
        if (allCover(graph)) {
            answer = Math.min(answer, depth);
            return;
        }

        int r, c;
        for (int i = idx; i < 100; ++i) {
            r = i / 10;
            c = i % 10;
            if (graph[r][c]) {
                for (int h = 5; h > 0; --h) {
                    if (r + h > 10 || c + h > 10)
                        continue;
                    if (paper[h] < 1)
                        continue;
                    if (check(graph, r, c, h)) {
                        --paper[h];
                        fill(graph, r, c, h, false);
                        dfs(i + h, depth + 1);
                        fill(graph, r, c, h, true);
                        ++paper[h];
                    }
                }
                break;
            }
        }
    }

    private static boolean allCover(boolean[][] graph) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (graph[i][j])
                    return false;
            }
        }
        return true;
    }

    private static void fill(boolean[][] graph, int x, int y, int l, boolean val) {
        for (int i = x; i < x + l; ++i) {
            for (int j = y; j < y + l; ++j) {
                graph[i][j] = val;
            }
        }
    }

    private static boolean check(boolean[][] graph, int x, int y, int l) {
        for (int i = x; i < x + l; ++i) {
            for (int j = y; j < y + l; ++j) {
                if (!graph[i][j])
                    return false;
            }
        }
        return true;
    }
}
