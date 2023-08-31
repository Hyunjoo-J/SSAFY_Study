package Implematation.p20057_마법사상어와토네이도;

import java.util.*;
import java.io.*;

public class p20057_HJ {
    static int N, ans;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] mx = {
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 좌
            {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1}, // 하
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 우
            {1, 1, 0, 0, 0, 0, -1, -1, -2, -1}, // 상
    };
    static int[][] my = {
            {1, 1, 0, 0, 0, 0, -1, -1, -2, -1}, // 좌
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 하
            {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1}, // 우
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 상
    };
    static int[] per = {1, 1, 2, 2, 7, 7, 10, 10, 5, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int dir = 0;
        int nx = N / 2, ny = N / 2;
        int move = 1; //두 번 갈 때마다 move 수 증가
        int check = 0;
        double sandy;
        next:
        while (true) {
            if (dir == 4)
                dir = 0;
            for (int i = 0; i < move; ++i) {
                nx += dx[dir];
                ny += dy[dir];
                sandy = map[nx][ny];
                if (sandy > 0)
                    spread(nx, ny, dir, sandy);
                if (nx == 0 && ny == 0) break next;
            }
            ++check;
            if (check == 2) {
                ++move;
                check = 0;
            }
            ++dir;
        }
        System.out.println(ans);
    }

    private static void spread(int x, int y, int d, double num) {
        int nx, ny;
        double use = 0; //움직인 모래
        map[x][y] -= num;
        for (int i = 0; i < 10; ++i) {
            nx = x + mx[d][i];
            ny = y + my[d][i];
            int sand = (int)Math.floor(num / 100 * per[i]);
            if (i == 9) { //a(알파)에 들어갈 것
                double a = num - use;
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                    ans += a;
                else
                    map[nx][ny] += a;
            } else {
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    ans += sand; // 범위 벗어난 모래 더함
                else
                    map[nx][ny] += sand;
                use += sand;
            }
        }
    }
}
