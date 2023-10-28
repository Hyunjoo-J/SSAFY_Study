package BFS_DFS.p18405_경쟁적전염;

import java.io.*;
import java.util.*;

public class p18405_HC {

    private static class Pair {
        int x, y, num;

        public Pair(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];
        List<Pair> virus = new ArrayList<>();
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] > 0)
                    virus.add(new Pair(i, j, graph[i][j]));
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        virus.stream()
                .sorted(Comparator.comparingInt(o -> o.num))
                .forEach(v -> {
                    visited[v.x][v.y] = true;
                    queue.add(v);
                });

        int nx, ny;
        int t = 0;
        while (t < S && !queue.isEmpty()) {
            ++t;
            int queueSize = queue.size();
            for (int q = 0; q < queueSize; ++q) {
                Pair p = queue.poll();

                for (int i = 0; i < 4; ++i) {
                    nx = p.x + dx[i];
                    ny = p.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (graph[nx][ny] > 0)
                        continue;
                    graph[nx][ny] = p.num;
                    queue.add(new Pair(nx, ny, p.num));
                    visited[nx][ny] = true;
                }
            }
        }
        System.out.println(graph[X][Y]);
        br.close();
    }
}