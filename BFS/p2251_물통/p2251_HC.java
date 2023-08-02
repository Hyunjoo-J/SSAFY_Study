package BFS.p2251_물통;

import java.io.*;
import java.util.*;

public class p2251_HC {

    static class Pair {
        int a, b, c;

        public Pair(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Queue<Pair> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[A + 1][B + 1][C + 1];

        queue.add(new Pair(0, 0, C));
        visited[0][0][C] = true;

        boolean[] checker = new boolean[C + 1];

        Pair p;
        while (!queue.isEmpty()) {
            p = queue.poll();

            if (p.a == 0) {
                checker[p.c] = true;
            }

            Pair[] nps = {
                new Pair(0, p.b + p.a, p.c),
                new Pair(p.a - (B - p.b), B, p.c),
                new Pair(0, p.b, p.c + p.a),
                new Pair(p.a - (C - p.c), p.b, C),
                new Pair(p.a + p.b, 0, p.c),
                new Pair(A, p.b - (A - p.a), p.c),
                new Pair(p.a, 0, p.c + p.b),
                new Pair(p.a, p.b - (C - p.c), C),
                new Pair(p.a + p.c, p.b, 0),
                new Pair(A, p.b, p.c - (A - p.a)),
                new Pair(p.a, p.b + p.c, 0),
                new Pair(p.a, B, p.c - (B - p.b))
            };
            for (Pair np: nps) {
                if (np.a < 0 || np.b < 0 || np.c < 0)
                    continue;
                if (np.a > A || np.b > B || np.c > C)
                    continue;
                if (visited[np.a][np.b][np.c])
                    continue;
                visited[np.a][np.b][np.c] = true;
                queue.add(np);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= C; ++i) {
            if (checker[i])
                sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
