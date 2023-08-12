package BFS_DFS.p13549_숨바꼭질3;

import java.io.*;
import java.util.*;

public class p13549_HC {

    static class Pair {
        int x, t;

        public Pair(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Pair> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        int[] time = {0, 1, 1};

        deque.add(new Pair(N, 0));
        visited[N] = true;
        while (!deque.isEmpty()) {
            Pair now = deque.pollFirst();

            visited[now.x] = true;

            if (now.x == K) {
                System.out.println(now.t);
                break;
            }

            int[] candidate = {now.x - 1, now.x + 1, now.x * 2};
            int next;
            for (int i = 0; i < 3; ++i) {
                next = candidate[i];
                if (next < 0 || next > 100000)
                    continue;
                if (visited[next])
                    continue;
                if (i == 2) {
                    deque.addFirst(new Pair(next, now.t));
                } else {
                    deque.addLast(new Pair(next, now.t + 1));
                }
            }
        }
    }
}