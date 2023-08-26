package Implematation.p20057_마법사상어와토네이도;

import java.io.*;
import java.util.*;

public class p20057_HC {

    private static final int BASE = 5;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 10][N + 10];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[BASE + i][BASE + j] = Integer.parseInt(st.nextToken());
            }
        }

        int N2 = N >> 1;
        int x = BASE + N2;
        int y = BASE + N2;
        int length = 0;

        for (int i = 0; i < N2; ++i) {
            ++length;
            for (int j = 0; j < length; ++j)
                tornado(x, y--, 0);

            for (int j = 0; j < length; ++j)
                tornado(x++, y, 1);

            ++length;
            for (int j = 0; j < length; ++j)
                tornado(x, y++, 2);

            for (int j = 0; j < length; ++j)
                tornado(x--, y, 3);
        }
        for (int j = 0; j < length; ++j)
            tornado(x, y--, 0);

        int answer = 0;
        for (int i = 0; i < N + 10; ++i) {
            for (int j = 0; j < N + 10; ++j) {
                if (i < BASE || j < BASE || i >= BASE + N || j >= BASE + N)
                    answer += graph[i][j];
            }
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void tornado(int x, int y, int dir) {
        int sand = graph[x + dx[dir]][y + dy[dir]];
        int one = (int) (sand * 0.01);
        int two = (int) (sand * 0.02);
        int five = (int) (sand * 0.05);
        int seven = (int) (sand * 0.07);
        int ten = (int) (sand * 0.1);
        int alpha = sand - 2 * (one + two + seven + ten) - five;

        graph[x + dx[dir]][y + dy[dir]] = 0;
        graph[x + 2 * dx[dir]][y + 2 * dy[dir]] += alpha;
        graph[x + dy[dir]][y + dx[dir]] += one;
        graph[x - dy[dir]][y - dx[dir]] += one;
        graph[x + dx[dir] + dy[dir]][y + dy[dir] + dx[dir]] += seven;
        graph[x + dx[dir] - dy[dir]][y + dy[dir] - dx[dir]] += seven;
        graph[x + dx[dir] + (2 * dy[dir])][y + dy[dir] + (2 * dx[dir])] += two;
        graph[x + dx[dir] - (2 * dy[dir])][y + dy[dir] - (2 * dx[dir])] += two;
        graph[x + (2 * dx[dir]) + dy[dir]][y + (2 * dy[dir]) + dx[dir]] += ten;
        graph[x + (2 * dx[dir]) - dy[dir]][y + (2 * dy[dir]) - dx[dir]] += ten;
        graph[x + 3 * dx[dir]][y + 3 * dy[dir]] += five;
    }
}
