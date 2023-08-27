package Implematation.p20057_마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p20057_YK {
    static int N, result;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1 1 2 2 3 3 4 4 5 5 6 6 7 7 ...
        // 반시계
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N / 2, y = N / 2;
        whirl(x, y, 1, 1, 2, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void whirl(int x, int y, int now, int next1, int next2, int dir) {
        if (x == 0 && y == 0) return;
        --now;

        int nx = x + dx[dir]; int ny = y + dy[dir];
        int dust = map[nx][ny];
        map[nx][ny] = 0;
        int alpha = getAlpha(dust);

        if (check(x + dy[dir], y + dx[dir])) map[x + dy[dir]][y + dx[dir]] += (int) (dust * 0.01);
        else result += (int) (dust * 0.01);
        if (check(x - dy[dir], y - dx[dir])) map[x - dy[dir]][y - dx[dir]] += (int) (dust * 0.01);
        else result += (int) (dust * 0.01);
        if (check(x + dx[dir] + dy[dir] * 2, y + dy[dir] + dx[dir] * 2)) map[x + dx[dir] + dy[dir] * 2][y + dy[dir] + dx[dir] * 2] += (int) (dust * 0.02);
        else result += (int) (dust * 0.02);
        if (check(x + dx[dir] - dy[dir] * 2, y + dy[dir] - dx[dir] * 2)) map[x + dx[dir] - dy[dir] * 2][y + dy[dir] - dx[dir] * 2] += (int) (dust * 0.02);
        else result += (int) (dust * 0.02);
        if (check(x + dx[dir] * 3, y + dy[dir] * 3)) map[x + dx[dir] * 3][y + dy[dir] * 3] += (int) (dust * 0.05);
        else result += (int) (dust * 0.05);
        if (check(x + dx[dir] + dy[dir], y + dy[dir] + dx[dir])) map[x + dx[dir] + dy[dir]][y + dy[dir] + dx[dir]] += (int) (dust * 0.07);
        else result += (int) (dust * 0.07);
        if (check(x + dx[dir] - dy[dir], y + dy[dir] - dx[dir])) map[x + dx[dir] - dy[dir]][y + dy[dir] - dx[dir]] += (int) (dust * 0.07);
        else result += (int) (dust * 0.07);
        if (check(x + dx[dir] * 2 + dy[dir], y + dy[dir] * 2 + dx[dir])) map[x + dx[dir] * 2 + dy[dir]][y + dy[dir] * 2 + dx[dir]] += (int) (dust * 0.1);
        else result += (int) (dust * 0.1);
        if (check(x + dx[dir] * 2 - dy[dir], y + dy[dir] * 2 - dx[dir])) map[x + dx[dir] * 2 - dy[dir]][y + dy[dir] * 2 - dx[dir]] += (int) (dust * 0.1);
        else result += (int) (dust * 0.1);
        if (check(x + dx[dir] * 2, y + dy[dir] * 2)) map[x + dx[dir] * 2][y + dy[dir] * 2] += alpha;
        else result += alpha;

        if (now == 0) {
            whirl(nx, ny, next1, next2, next1 == next2 ? next1 + 1 : next2, (dir + 1) % 4);
        }
        else {
            whirl(nx, ny, now, next1, next2, dir);
        }
    }

    private static int getAlpha(int dust) {
        int tmp = dust;
        // (int)(tmp * 0.02) 랑 (int) (tmp * 0.01) * 2 가 뭔 차이지????
        dust -= (int) (tmp * 0.01) * 2;
        dust -= (int) (tmp * 0.07) * 2;
        dust -= (int) (tmp * 0.02) * 2;
        dust -= (int) (tmp * 0.1) * 2;
        dust -= (int) (tmp * 0.05);
        return dust;
    }

    private static boolean check(int a, int b) {
        return a >= 0 && a < N && b >= 0 && b < N;
    }
}
