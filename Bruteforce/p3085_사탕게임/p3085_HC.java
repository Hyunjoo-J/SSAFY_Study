package Bruteforce.p3085_사탕게임;

import java.io.*;
import java.util.*;

public class p3085_HC {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[][] graph = new char[N][N];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                graph[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        int nx, ny;
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                for (int i = 0; i < 4; ++i) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;
                    if (graph[x][y] == graph[nx][ny])
                        continue;
                    swap(graph, x, y, nx, ny);
                    answer = Math.max(answer, getCandyCount(graph, N));
                    swap(graph, nx, ny, x, y);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCandyCount(char[][] graph, int N) {
        int temp, max = 0;
        char c;
        for (int i = 0; i < N; ++i) {
            temp = 1;
            c = ' ';
            for (int j = 0; j < N; ++j) {
                if (graph[i][j] != c) {
                    c = graph[i][j];
                    temp = 1;
                } else {
                    temp += 1;
                }
                max = Math.max(max, temp);
            }

            temp = 1;
            c = ' ';
            for (int j = 0; j < N; ++j) {
                if (graph[j][i] != c) {
                    c = graph[j][i];
                    temp = 1;
                } else {
                    temp += 1;
                }
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    private static void swap(char[][] graph, int x1, int y1, int x2, int y2) {
        char temp = graph[x1][y1];
        graph[x1][y1] = graph[x2][y2];
        graph[x2][y2] = temp;
    }
}
