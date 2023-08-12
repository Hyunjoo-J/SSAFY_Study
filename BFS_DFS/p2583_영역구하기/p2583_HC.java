package BFS_DFS.p2583_영역구하기;

import java.io.*;
import java.util.*;

public class p2583_HC {

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int N, M;
    private static int[][] graph;
    private static boolean[][] visited;
    private static Queue<Pair> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K, x1, y1, x2, y2;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[M + 1][N + 1];
        visited = new boolean[M][N];

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            graph[y1][x1] += 1;
            graph[y1][x2] -= 1;
            graph[y2][x1] -= 1;
            graph[y2][x2] += 1;
        }

        for (int i = M; i >= 0; --i) {
            for (int j = 1; j <= N; ++j) {
                graph[i][j] += graph[i][j - 1];
            }
        }

        for (int j = 0; j <= N; ++j) {
            for (int i = 1; i <= M; ++i) {
                graph[i][j] += graph[i - 1][j];
            }
        }

        int count = 0;
        List<Integer> result = new ArrayList<>(10);
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (graph[i][j] == 0 && !visited[i][j]) {
                    ++count;
                    result.add(bfs(i, j));
                }
            }
        }
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for (int area: result)
            sb.append(area).append(" ");
        System.out.println(sb.toString());
        br.close();
    }

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int bfs(int y, int x) {
        int ny, nx, ret = 1;
        queue.offer(new Pair(y, x));
        visited[y][x] = true;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; ++i) {
                ny = p.y + dy[i];
                nx = p.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= M || nx >= N)
                    continue;
                if (visited[ny][nx])
                    continue;
                if (graph[ny][nx] > 0)
                    continue;
                visited[ny][nx] = true;
                queue.add(new Pair(ny, nx));
                ++ret;
            }
        }
        return ret;
    }
}