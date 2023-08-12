package BFS_DFS.p16236_아기상어;

import java.io.*;
import java.util.*;

public class p16236_HC {

    static class Pair {
        int x, y, t;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int answer = 0;
    private static int N, sharkSize = 2;
    private static int eat = 0;
    private static int[][] graph;
    private static Pair shark;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    shark = new Pair(i, j);
                    graph[i][j] = 0;
                }
            }
        }

        Pair target;
        while ((target = bfs(shark.x, shark.y)) != null) {
            graph[target.x][target.y] = 0;
            ++eat;
            if (eat == sharkSize) {
                ++sharkSize;
                eat = 0;
            }
            answer += target.t;
            shark = target;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static Pair bfs(int sx, int sy) {
        Pair target = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Pair(sx, sy));
        visited[sx][sy] = true;

        int nx, ny, t = 0;
        boolean find = false;
        while (!queue.isEmpty()) {
            ++t;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; ++i) {
                Pair p = queue.poll();

                for (int j = 0; j < 4; ++j) {
                    nx = p.x + dx[j];
                    ny = p.y + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (graph[nx][ny] > sharkSize)
                        continue;
                    if (0 < graph[nx][ny] && graph[nx][ny] < sharkSize) {
                        find = true;
                        target = getHighPriority(target, new Pair(nx, ny));
                        continue;
                    }
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            if (find) {
                target.t = t;
                return target;
            }
        }
        return null;
    }

    private static Pair getHighPriority(Pair p1, Pair p2) {
        if (p1.x < p2.x) {
            return p1;
        } else if (p1.x > p2.x) {
            return p2;
        }
        return p1.y < p2.y ? p1 : p2;
    }
}
