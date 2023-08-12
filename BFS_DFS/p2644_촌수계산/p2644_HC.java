package BFS_DFS.p2644_촌수계산;

import java.io.*;
import java.util.*;

public class p2644_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int x, y;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[n + 1];
        queue.add(a);
        visited[a] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next: graph.get(now)) {
                if (visited[next] > 0)
                    continue;
                queue.add(next);
                visited[next] = visited[now] + 1;
            }
        }
        bw.write(String.valueOf(visited[b] - 1));

        bw.flush();
        bw.close();
        br.close();
    }
}
