package BFS_DFS.p9328_열쇠;

import java.io.*;
import java.util.*;
public class p9328_YK {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int H, W;
    static char[][] map = new char[102][102];
    static int keys = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] isDoc; // 찾았던 문서를 또 찾지 않도록

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            isDoc = new boolean[H + 2][W + 2];

            for (int i = 1; i <= H; ++i) {
                String line = br.readLine();
                for (int j = 0; j < W; ++j) {
                    map[i][j + 1] = line.charAt(j);
                    if (map[i][j + 1] == '$') isDoc[i][j + 1] = true;
                }
                map[i][0] = '.';
                map[i][W + 1] = '.';
            }
            Arrays.fill(map[H + 1], '.');

            keys = 0;
            char[] keyList = br.readLine().toCharArray();
            for (char key : keyList) {
                if (key == '0') break;
                keys |= (1 << (key - 'A'));
            }

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int bfs() {
        boolean[][] visited = new boolean[H + 2][W + 2];
        visited[0][0] = true;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));

        int result = 0;
        while (!q.isEmpty()) {
            Point now = q.poll();
            char ch = map[now.x][now.y];

            // 문서
            if (ch == '$' && isDoc[now.x][now.y]) {
                isDoc[now.x][now.y] = false;
                ++result;
            }

            // 열쇠 (새로운 열쇠를 찾으면 visited 리셋)
            if (ch >= 'a' && ch <= 'z') {
                // 가지고 있지 않을 때만
                if ((keys & (1 << (ch - 'a'))) == 0) {
                    keys |= (1 << (ch - 'a'));
                    visited = new boolean[H + 2][W + 2];
                    visited[now.x][now.y] = true;
                }
            }

            for (int i = 0; i < 4; ++i) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                // 범위 밖
                if (nx < 0 || nx >= H + 2 || ny < 0 || ny >= W + 2) continue;
                // 벽
                if (map[nx][ny] == '*') continue;
                // 방문한 적 있음
                if (visited[nx][ny]) continue;
                // 문인데 열쇠 없음
                if (isDoor(nx, ny) && !isValidKey(map[nx][ny])) continue;

                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }

        return result;
    }

    private static boolean isValidKey(char door) {
        return ((1 << (door - 'A')) & keys) != 0;
    }

    private static boolean isDoor(int x, int y) {
        return map[x][y] >= 'A' && map[x][y] <= 'Z';
    }
}
