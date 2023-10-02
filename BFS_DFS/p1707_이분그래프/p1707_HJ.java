package BFS_DFS.p1707_이분그래프;
import java.util.*;
import java.io.*;

public class p1707_HJ {
    static int K, V, E;
    static List<Integer>[] list;
    static int[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= K; ++tc) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            list = new ArrayList[V + 1];
            visited = new int[V + 1];
            for (int i = 1; i <= V; ++i)
                list[i] = new ArrayList<>();
            for (int i = 0; i < E; ++i) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
                list[v].add(u);
            }
            color();
        }
        System.out.print(sb.toString());
    }

    private static void color() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= V; ++i) {
            if (visited[i] == 0) {
                q.add(i);
                visited[i] = 1;
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int con : list[cur]) {
                    if (visited[con] == 0) {
                        q.add(con);
                    }
                    if (visited[con] == visited[cur]) {
                        sb.append("NO\n");
                        return;
                    }
                    if (visited[cur] == 1 && visited[con] == 0)
                        visited[con] = 2;
                    else if (visited[cur] == 2 && visited[con] == 0)
                        visited[con] = 1;
                }
            }
        }
        sb.append("YES\n");
    }
}
