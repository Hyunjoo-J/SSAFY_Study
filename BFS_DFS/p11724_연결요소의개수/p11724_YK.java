package BFS_DFS.p11724_연결요소의개수;

import java.io.*;
import java.util.*;

public class p11724_YK {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int result = 0;
        for (int i = 1; i <= N; ++i) {
            if (visited[i]) continue;
            ++result;
            bfs(i);
        }

        System.out.println(result);
        br.close();
    }

    private static void bfs(int k) {
        visited[k] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
