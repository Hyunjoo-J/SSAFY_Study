package BFS_DFS.프로그래머스LV2_1829_카카오프렌즈컬러링북;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_YK {

    boolean[][] visited;
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    Queue<Point> q = new ArrayDeque<>();

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 0) continue;
                if (visited[i][j]) continue;
                ++numberOfArea;
                visited[i][j] = true;
                maxSizeOfOneArea = Math.max(bfs(picture, i, j), maxSizeOfOneArea);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int bfs(int[][] picture, int x, int y) {
        q.add(new Point(x, y));
        int color = picture[x][y];
        int cnt = 0;

        int m = picture.length; int n = picture[0].length;

        while (!q.isEmpty()) {
            Point p = q.poll();
            ++cnt;

            for (int i = 0, nx, ny; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (picture[nx][ny] != color) continue;

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }

        return cnt;
    }
}
