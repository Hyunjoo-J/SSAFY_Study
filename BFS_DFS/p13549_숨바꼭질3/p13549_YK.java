package BFS_DFS.p13549_숨바꼭질3;

import java.io.*;
import java.util.*;

public class p13549_YK {

    static class Point implements Comparable<Point> {
        int x, time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(time, o.time);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(K, N));
        br.close();
    }

    private static int bfs(int k, int n) {
        boolean[][] visited = new boolean[100_001][2];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        visited[k][0] = true;
        pq.offer(new Point(k, 0));

        int newX;
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.x == n) return cur.time;

            newX = cur.x / 2;
            if (cur.x > 1 && cur.x % 2 == 0 && !visited[newX][1]) {
                visited[newX][1] = true;
                pq.offer(new Point(newX, cur.time));
            }

            newX = cur.x - 1;
            if (cur.x > 0 && !visited[newX][0]) {
                visited[newX][0] = true;
                pq.offer(new Point(newX, cur.time + 1));
            }

            newX = cur.x + 1;
            if (cur.x < 100_000 && !visited[newX][0]) {
                visited[newX][0] = true;
                pq.offer(new Point(newX, cur.time + 1));
            }
        }
        return -1;
    }
}
