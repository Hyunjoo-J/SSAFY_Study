package Implematation.p14503_로봇청소기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p14503_YK {

    static class Robot {
        int x;
        int y;
        int dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        // 1을 서쪽, 3을 동쪽으로 바꾼다.
        Robot robot = new Robot(x, y, dir % 2 == 0 ? dir : (dir + 2) % 4);

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int nx, ny, nd;
        int result = 0;
        while (true) {
            x = robot.x; y = robot.y; dir = robot.dir;
            flag = true;
            // 청소가 되어 있지 않다면 청소한다.
            if (map[x][y] == 0 && !visited[x][y]) {
                visited[x][y] = true;
                ++result;
            }
            // 현재 칸 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
            for (int i = 1; i <= 4; ++i) {
                // 90도 회전
                nd = (dir + i) % 4;
                nx = x + dx[nd];
                ny = y + dy[nd];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;

                // 앞 쪽 칸이 범위 밖이 아니고
                // 청소되지 않은 (방문하지 않은) 빈 칸인 경우
                flag = false;
                robot.x = nx; robot.y = ny; robot.dir = nd;
                break;
            }

            if (flag) {
                nx = x - dx[dir];
                ny = y - dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                if (map[nx][ny] == 1) break;
                robot.x = nx; robot.y = ny;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
