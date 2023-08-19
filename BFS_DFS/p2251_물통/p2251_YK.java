package BFS_DFS.p2251_물통;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2251_YK {
    static int A, B, C;
    static boolean[][][] visited;
    static boolean[] result;
    static Queue<Buckets> q = new LinkedList<>();
    static class Buckets {
        int a;
        int b;
        int c;
        public Buckets(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1][C + 1];
        result = new boolean[C + 1];
        bfs(C);

        for (int i = 0; i <= C; ++i) {
            if (!result[i]) continue;
            bw.write(String.valueOf(i));
            bw.write(" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int c) {
        visited[0][0][c] = true;
        q.offer(new Buckets(0, 0, c));

        while (!q.isEmpty()) {
            Buckets now = q.poll();
            if (now.a == 0) result[now.c] = true;

            int na, nb, nc;
            // from A
            if (A > 0) {
                // A -> B
                na = now.b + now.a > B ? now.a - (B - now.b) : 0;
                nb = Math.min(now.b + now.a, B);
                if (!visited[na][nb][now.c]) {
                    visited[na][nb][now.c] = true;
                    q.offer(new Buckets(na, nb, now.c));
                }

                // A -> C
                na = now.c + now.a > C ? now.a - (C - now.c) : 0;
                nc = Math.min(now.c + now.a, C);
                if (!visited[na][now.b][nc]) {
                    visited[na][now.b][nc] = true;
                    q.offer(new Buckets(na, now.b, nc));
                }

            }

            // from B
            if (B > 0) {
                // B -> C
                nb = now.c + now.b > C ? now.b - (C - now.c) : 0;
                nc = Math.min(now.c + now.b, C);
                if (!visited[now.a][nb][nc]) {
                    visited[now.a][nb][nc] = true;
                    q.offer(new Buckets(now.a, nb, nc));
                }

                // B -> A
                nb = now.a + now.b > A ? now.b - (A - now.a) : 0;
                na = Math.min(now.a + now.b, A);
                if (!visited[na][nb][now.c]) {
                    visited[na][nb][now.c] = true;
                    q.offer(new Buckets(na, nb, now.c));
                }
            }

            // from C
            if (C > 0) {
                // C -> B
                nc = now.b + now.c > B ? now.c - (B - now.b) : 0;
                nb = Math.min(now.b + now.c, B);
                if (!visited[now.a][nb][nc]) {
                    visited[now.a][nb][nc] = true;
                    q.offer(new Buckets(now.a, nb, nc));
                }

                // C -> A
                nc = now.a + now.c > A ? now.c - (A - now.a) : 0;
                na = Math.min(now.a + now.c, A);
                if (!visited[na][now.b][nc]) {
                    visited[na][now.b][nc] = true;
                    q.offer(new Buckets(na, now.b, nc));
                }
            }
        }
    }

}
