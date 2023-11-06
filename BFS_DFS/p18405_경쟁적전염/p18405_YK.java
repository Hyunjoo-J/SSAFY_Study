package BFS_DFS.p18405_경쟁적전염;

import java.io.*;
import java.util.*;

public class p18405_YK {
    static class Virus implements Comparable<Virus>{
        int num, x, y, stage;

        public Virus(int num, int x, int y, int stage) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.stage = stage;
        }

        @Override
        public int compareTo(Virus o) {
            return Integer.compare(this.num, o.num);
        }
    }

    static int N, K;
    static int[][] map;
    static Queue<Virus> q = new ArrayDeque<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];


        PriorityQueue<Virus> pq = new PriorityQueue<>();
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    pq.offer(new Virus(map[i][j], i, j, -1));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        while (!pq.isEmpty()) {
            q.offer(pq.poll());
        }

        for (int stage = 0; stage < S; ++stage) {
            while (q.peek().stage < stage) {
                Virus cur = q.poll();
                ++cur.stage;
                for (int i = 0; i < 4; ++i) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (map[nx][ny] != 0) continue;
                    q.offer(new Virus(cur.num, nx, ny, cur.stage));
                    map[nx][ny] = cur.num;
                    if (map[X][Y] != 0) break;
                }
                q.offer(cur);
                if (map[X][Y] != 0) break;
            }
            if (map[X][Y] != 0) break;
        }

        System.out.println(map[X][Y]);
        br.close();
    }
}
