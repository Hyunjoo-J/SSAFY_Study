package Implematation.p20056_마법사상어와파이어볼;

import java.io.*;
import java.util.*;

public class p20056_HC {

    static class Fireball {
        int m, s, d;

        public Fireball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int N;
    private static Queue<Fireball>[][] graph;
    private static Queue<Fireball>[][] graph2;
    private static Queue<Fireball>[][] temp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = generateEmptyMap();
        graph2 = generateEmptyMap();

        int r, c, m, s, d;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            graph[r][c].add(new Fireball(m, s, d));
        }

        while (K-- > 0) {
            fireMove(); // 1
            fireCombineAndDivide(); // 2
        }
        bw.write(String.valueOf(getTotalMass()));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void fireMove() {
        int nx, ny;
        Fireball fb;
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                while (!graph[x][y].isEmpty()) {
                    fb = graph[x][y].poll();
                    nx = ((x + dx[fb.d] * fb.s) + N * fb.s) % N;
                    ny = ((y + dy[fb.d] * fb.s) + N * fb.s) % N;
                    graph2[nx][ny].add(new Fireball(fb.m, fb.s, fb.d));
                }
            }
        }
        temp = graph;
        graph = graph2;
        graph2 = temp;
    }

    private static void fireCombineAndDivide() {
        int mass, speed, count;
        int[] dCounter = new int[2];
        Fireball fb;

        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                if (graph[x][y].size() == 1) {
                    graph2[x][y].add(graph[x][y].poll());
                    continue;
                }
                mass = 0;
                speed = 0;
                count = 0;
                Arrays.fill(dCounter, 0);

                while (!graph[x][y].isEmpty()) {
                    fb = graph[x][y].poll();
                    mass += fb.m;
                    speed += fb.s;
                    ++count;
                    ++dCounter[fb.d & 1];
                }
                if (mass / 5 == 0)  // 소멸
                    continue;
                if (dCounter[0] == 0 || dCounter[1] == 0) {
                    for (int d = 0; d < 8; d += 2) {
                        graph2[x][y].add(new Fireball(mass / 5, speed / count, d));
                    }
                } else {
                    for (int d = 1; d < 8; d += 2) {
                        graph2[x][y].add(new Fireball(mass / 5, speed / count, d));
                    }
                }
            }
        }
        temp = graph;
        graph = graph2;
        graph2 = temp;
    }

    private static int getTotalMass() {
        int mass = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (Fireball fb: graph[i][j]) {
                    mass += fb.m;
                }
            }
        }
        return mass;
    }

    private static Queue<Fireball>[][] generateEmptyMap() {
        Queue<Fireball>[][] graph = new LinkedList[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j)
                graph[i][j] = new LinkedList<>();
        }
        return graph;
    }
}
