package Implematation.p20056_마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class p20056_YK {

    static class FireBall {
        int m, d, s;
        int stage;
        public FireBall(int m, int s, int d, int stage) {
            this.m = m;
            this.d = d;
            this.s = s;
            this.stage = stage;
        }
    }

    static int N, M, K;
    static Queue<FireBall>[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Queue[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                map[i][j] = new ArrayDeque<>();
            }
        }

        int r, c, m, s, d;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            map[r][c].add(new FireBall(m, s, d, -1));
        }

        for (int i = 0; i < K; ++i) {
            // 1. 모든 파이어볼의 이동
            move(i);
            // 2. 두 개 이상의 파이어 볼이 있는 칸
            check(i);
        }

        System.out.println(cal());
        br.close();
    }

    private static int cal() {
        int sum = 0;
        FireBall cur;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                while (!map[i][j].isEmpty()) {
                    cur = map[i][j].poll();
                    sum += cur.m;
                }
            }
        }
        return sum;
    }

    private static void move(int stage) {
        int size, nr, nc;
        FireBall cur;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                size = map[i][j].size();
                for (int k = 0; k < size; ++k) {
                    if (map[i][j].peek().stage == stage) {
                        break;
                    }
                    cur = map[i][j].poll();
                    ++cur.stage;
                    nr = i + dr[cur.d] * cur.s;
                    nc = j + dc[cur.d] * cur.s;

                    while (nr >= N) {
                        nr -= N;
                    }
                    while (nr < 0) {
                        nr += N;
                    }
                    while (nc >= N) {
                        nc -= N;
                    }
                    while (nc < 0) {
                        nc += N;
                    }

                    map[nr][nc].offer(cur);
                }
            }
        }
    }

    private static void check(int stage) {
        boolean flag;
        int size, mod, mass, speed;
        FireBall cur;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                size = map[i][j].size();
                if (size <= 1) continue;
                flag = true;
                mod = map[i][j].peek().d % 2;
                mass = 0; speed = 0;
                while (!map[i][j].isEmpty()) {
                    cur = map[i][j].poll();
                    if (flag && cur.d % 2 != mod) flag = false;
                    mass += cur.m;
                    speed += cur.s;
                }

                if (mass < 5) continue;
                mass /= 5;
                speed /= size;
                int d = flag ? 0 : 1;
                while (d < 8) {
                    map[i][j].offer(new FireBall(mass, speed, d, stage));
                    d += 2;
                }
            }
        }
    }
}
