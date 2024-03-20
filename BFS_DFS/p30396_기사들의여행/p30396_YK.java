package BFS_DFS.p30396_기사들의여행;

import java.io.*;
import java.util.*;

public class p30396_YK {

    static class Knight {
        int id, round;

        public Knight(int id, int round) {
            this.id = id;
            this.round = round;
        }
    }

    static class Now {
        Knight[][] map;
        int[][] visited;
        int round;

        public Now(Knight[][] map, int[][] visited, int round) {
            this.map = map;
            this.visited = visited;
            this.round = round;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] answer = new int[4][4];
        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

        Knight[][] initialMap = new Knight[4][4];
        int[][] initialVisited = new int[4][4];

        for (int i = 0, cnt = 1; i < 4; ++i) {
            String line = br.readLine();
            for (int j = 0; j < 4; ++j) {
                if (line.charAt(j) == '0') continue;
                initialVisited[i][j] |= (1 << cnt);
                initialMap[i][j] = new Knight(cnt++, 0);
            }
        }

        for (int i = 0; i < 4; ++i) {
            String line = br.readLine();
            for (int j = 0; j < 4; ++j) {
                if (line.charAt(j) == '0') continue;
                answer[i][j] = 1;
            }
        }

        Queue<Now> q = new ArrayDeque<>();
        q.add(new Now(initialMap, initialVisited, 0));
        while (!q.isEmpty()) {
            Now n = q.poll();

            if (isEnd(n.map, answer)) {
                System.out.println(n.round);
                break;
            }

            for (int x = 0; x < 4; ++x) {
                for (int y = 0; y < 4; ++y) {
                    if (n.map[x][y] == null) continue;
                    if (n.map[x][y].round > n.round) continue;
                    for (int i = 0, nx, ny; i < 8; ++i) {
                        nx = x + dx[i];
                        ny = y + dy[i];

                        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
                        if ((n.visited[nx][ny] & (1 << n.map[x][y].id)) != 0) continue;

                        Knight[][] map = new Knight[4][4];
                        int[][] visited = new int[4][4];
                        for (int j = 0; j < 4; ++j) {
                            map[j] = n.map[j].clone();
                            visited[j] = n.visited[j].clone();
                        }

                        map[nx][ny] = map[x][y];
                        map[x][y] = null;
                        visited[nx][ny] |= (1 << map[nx][ny].id);

                        q.add(new Now(map, visited, n.round + 1));
                    }
                }
            }
        }

        br.close();
    }

    private static boolean isEnd(Knight[][] map, int[][] answer) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (answer[i][j] == 0) continue;
                if (map[i][j] == null) return false;
            }
        }
        return true;
    }
}
