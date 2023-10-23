package TwoPointer.p2842_집배원한상덕;

import java.io.*;
import java.util.*;

public class p2842_YK {
    static int N, sx, sy, houseCnt, result = Integer.MAX_VALUE;
    static char[][] map;
    static int[][] height;
    static int[] h;
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dy = {0, -1, 0, 1, 1, -1, -1, 1};

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        height = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'P') {
                    sx = i; sy = j;
                }
                if (map[i][j] == 'K') ++houseCnt;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                height[i][j] = Integer.parseInt(st.nextToken());
                set.add(height[i][j]);
            }
        }

        h = set.stream().sorted().mapToInt(Integer::intValue).toArray();
        if (h.length == 1) result = 0;
        else {
            int p1 = 0, p2 = 1;
            while (result != 0 && p1 <= p2 && p2 < h.length) {
                if (bfs(h[p1], h[p2])) {
                    result = Math.min(result, h[p2] - h[p1]);
                    p1++;
                } else {
                    p2++;
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int min, int max) {
        if (min > height[sx][sy] || max < height[sx][sy]) return false;

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(sx, sy));
        boolean[][] visited = new boolean[N][N];
        visited[sx][sy] = true;

        int k = 0, nx, ny;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (map[cur.x][cur.y] == 'K') ++k;
            if (k == houseCnt) return true;

            for (int i = 0; i < 8; ++i) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (height[nx][ny] < min || height[nx][ny] > max) continue;
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }

        return k == houseCnt;
    }
}
