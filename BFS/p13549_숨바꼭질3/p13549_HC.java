package BFS.p13549_숨바꼭질3;

import java.io.*;
import java.util.*;

public class p13549_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(N);
        int[] visited = new int[100001];
        visited[N] = 1;

        while (!deque.isEmpty()) {
            int now = deque.pollFirst();

            int[] candidate = {now * 2, now - 1, now + 1};
            for (int i = 0; i < 3; ++i) {
                int next = candidate[i];
                if (next < 0 || next > 100000)
                    continue;
                if (visited[next] > 0)
                    continue;
                if (i == 0) {
                    deque.addFirst(next);
                    visited[next] = visited[now];
                } else {
                    deque.addLast(next);
                    visited[next] = visited[now] + 1;
                }
            }
        }
        bw.write(String.valueOf(visited[K] - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}
