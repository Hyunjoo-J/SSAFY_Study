package BFS_DFS.프로그래머스LV3_92343_양과늑대;

import java.io.*;
import java.util.*;

public class Solution_YK {

    static class Point {
        int x, sheep, wolf, visit;

        public Point(int x, int sheep, int wolf, int visit) {
            this.x = x;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visit = visit;
        }
    }
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        int N = info.length;

        boolean[][][] visited = new boolean[N][N + 1][N + 1];
        List<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 1, 0, 1));
        visited[0][1][0] = true;
        while (!q.isEmpty()) {

            Point p = q.poll();
            if (p.sheep > answer) answer = p.sheep;

            for (int nx : graph[p.x]) {
                if ((p.visit & (1 << nx)) != 0) {
                    // 방문한 적은 있는데 이 상태로 방문한 적은 없음
                    if (visited[nx][p.sheep][p.wolf]) continue;
                    visited[nx][p.sheep][p.wolf] = true;
                    q.offer(new Point(nx, p.sheep, p.wolf, p.visit));

                } else {
                    // 방문한 적이 없음
                    if (info[nx] == 1 && p.sheep == p.wolf + 1) continue;
                    int visit = p.visit | (1 << nx);
                    if (info[nx] == 0) {
                        visited[nx][p.sheep + 1][p.wolf] = true;
                        q.offer(new Point(nx, p.sheep + 1, p.wolf, visit));
                    } else {
                        visited[nx][p.sheep][p.wolf + 1] = true;
                        q.offer(new Point(nx, p.sheep, p.wolf + 1, visit));
                    }
                }
            }
        }

        return answer;
    }
}
