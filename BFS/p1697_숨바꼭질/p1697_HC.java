package BFS.p1697_숨바꼭질;

import java.io.*;
import java.util.*;

public class p1697_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        int[] visited = new int[100001];
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == K)
                break;

            int[] candidate = {now - 1, now + 1, now * 2};
            for (int next: candidate) {
                if (next < 0 || next > 100000)
                    continue;
                if (visited[next] > 0)
                    continue;
                queue.add(next);
                visited[next] = visited[now] + 1;
            }
        }
        bw.write(String.valueOf(visited[K] - 1));

        bw.flush();
        bw.close();
        br.close();
    }
}
