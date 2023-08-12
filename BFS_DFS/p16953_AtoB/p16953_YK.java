package BFS_DFS.p16953_AtoB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p16953_YK {
    static int A, B;
    static final int MAX = 1_000_000_000;
    static boolean[] visited = new boolean[MAX + 1];

    static class Point {
        long x;
        int cnt;
        Point(long x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        visited[A] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(A, 1));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.x == B) return now.cnt;

            if (now.x * 2 > B) continue;
            if(visited[(int) (now.x * 2)]) continue;
            visited[(int) (now.x * 2)] = true;
            q.offer(new Point(now.x * 2, now.cnt + 1));

            if (now.x * 10 + 1 > B) continue;
            if (visited[(int) (now.x * 10 + 1)]) continue;
            visited[(int) (now.x * 10 + 1)] = true;
            q.offer(new Point(now.x * 10 + 1, now.cnt + 1));
        }
        return -1;
    }
}
