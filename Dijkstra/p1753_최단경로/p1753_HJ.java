package Dijkstra.p1753_최단경로;
import java.util.*;
import java.io.*;

public class p1753_HJ {
    static class Node implements Comparable<Node>{
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int V, E, S;
    private static final int INF = Integer.MAX_VALUE;
    static List<Node>[] list;
    static int[] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        for(int i = 0; i <= V; ++i) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }
        dijkstra(S);
        for(int i = 1; i < V + 1; ++i) {
            if(dist[i] == INF)
                sb.append("INF\n");
            else
                sb.append(dist[i]+"\n");
        }
        System.out.println(sb.toString());

    }
    private static void dijkstra(int s) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];
        q.offer(new Node(s, 0));
        dist[s] = 0;
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int cur = curNode.v;
            if(visited[cur])
                continue;
            for(Node node : list[cur]) {
                if(dist[node.v] > dist[cur] + node.weight) {
                    dist[node.v] = dist[cur] + node.weight;
                    q.offer(new Node(node.v, dist[node.v]));
                }
            }
        }
    }

}
