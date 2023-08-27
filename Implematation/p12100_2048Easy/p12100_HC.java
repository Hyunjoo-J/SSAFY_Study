package Implematation.p12100_2048Easy;

import java.io.*;
import java.util.*;

public class p12100_HC {

    private static int answer = -1;
    private static final Queue<Integer> gQueue = new ArrayDeque<>(22);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(graph, N, 0);
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int[][] graph, int n, int depth) {
        if (depth >= 5) {
            answer = Math.max(answer, findMax(graph, n));
            return;
        }
        int[][] moved;
        for (int i = 0; i < 4; ++i) {
            graph = rotateDegree90(graph, n);
            moved = move(graph, n);
            dfs(moved, n,depth + 1);
        }
    }

    private static int findMax(int[][] graph, int n) {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, graph[i][j]);
            }
        }
        return res;
    }

    private static int[][] move(int[][] graph, int n) {
        Queue<Integer> queue = gQueue;
        int[][] copied = deepCopy(graph, n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (copied[i][j] > 0) {
                    queue.add(copied[i][j]);
                    copied[i][j] = 0;
                }
            }
            for (int j = 0; j < n && !queue.isEmpty(); ++j) {
                copied[i][j] = queue.poll();
                if (!queue.isEmpty() && copied[i][j] == queue.peek()) {
                    copied[i][j] <<= 1;
                    queue.poll();
                }
            }
        }
        return copied;
    }

    private static int[][] deepCopy(int[][] graph, int n) {
        int[][] copied = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                copied[i][j] = graph[i][j];
            }
        }
        return copied;
    }

    private static int[][] rotateDegree90(int[][] graph, int n) {
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                rotated[i][j] = graph[n - j - 1][i];
            }
        }
        return rotated;
    }
}