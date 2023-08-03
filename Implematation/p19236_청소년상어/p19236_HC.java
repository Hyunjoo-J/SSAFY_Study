package Implematation.p19236_청소년상어;

import java.io.*;
import java.util.*;

public class p19236_HC {

    static class Fish {
        int size, dir;

        public Fish(int size, int dir) {
            this.size = size;
            this.dir = dir;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    private static final int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    private static final int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    private static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Fish[][] graph = new Fish[4][4];
        for (int i = 0; i < 4; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j += 2) {
                graph[i][j >> 1] = new Fish(Integer.parseInt(st.nextToken()),
                                        Integer.parseInt(st.nextToken()) - 1);
            }
        }

        int firstSize = graph[0][0].size;
        graph[0][0].size = 99;
        dfs(graph, firstSize);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(Fish[][] graph, int sum) {
        ans = Math.max(ans, sum);

        // 물고기 이동
        fishMove(graph);

        Pair p = getFishPosition(graph, 99);
        Fish shark = graph[p.x][p.y];

        Fish[][] copied;
        int fishSize, nx = p.x, ny = p.y;
        for (int i = 0; i < 3; ++i) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                break;
            if (graph[nx][ny].size < 1 || graph[nx][ny].size > 16)
                continue;
            copied = deepCopy(graph);
            fishSize = copied[nx][ny].size;
            copied[p.x][p.y].size = -1;
            copied[nx][ny].size = 99;
            dfs(copied, sum + fishSize);
        }
    }

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    private static void fishMove(Fish[][] graph) {
        Pair p;
        Fish f;
        int nx, ny;
        for (int i = 1; i < 17; ++i) {
            p = getFishPosition(graph, i);
            if (p == null)
                continue;
            f = graph[p.x][p.y];
            f.dir = (f.dir + 7) % 8;
            for (int j = 0; j < 8; ++j) {
                f.dir = (f.dir + 1) % 8;
                nx = p.x + dx[f.dir];
                ny = p.y + dy[f.dir];
                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                    continue;
                if (graph[nx][ny].size == 99)
                    continue;
                swap(graph, p.x, p.y, nx, ny);
                break;
            }
        }
    }

    private static void swap(Fish[][] graph, int x1, int y1, int x2, int y2) {
        Fish temp = graph[x1][y1];
        graph[x1][y1] = graph[x2][y2];
        graph[x2][y2] = temp;
    }

    private static Pair getFishPosition(Fish[][] graph, int size) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (graph[i][j].size == size)
                    return new Pair(i, j);
            }
        }
        return null;
    }

    private static Fish[][] deepCopy(Fish[][] graph) {
        Fish[][] copied = new Fish[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                copied[i][j] = new Fish(graph[i][j].size, graph[i][j].dir);
            }
        }
        return copied;
    }
}
