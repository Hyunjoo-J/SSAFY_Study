package Dijkstra.p9370_미확인도착지;
import java.util.*;
import java.io.*;

public class p9370_HJ {
    static class Node implements Comparable<Node>{
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    private static final int INF = Integer.MAX_VALUE >> 2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        List<Node>[] list = new ArrayList[2001];
        int[] distS = new int[2001];
        int[] distG = new int[2001];
        int[] distH = new int[2001];
        int[] candi = new int[101];
        while (T-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for(int i = 1; i <= n; ++i)
                list[i] = new ArrayList<>();

            for(int i = 0; i < m ;++i){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[a].add(new Node(b, d));
                list[b].add(new Node(a, d));
            }

            dijkstra(list, n, s, distS);
            dijkstra(list, n, g, distG);
            dijkstra(list, n, h, distH);

            int cnt = 0;
            for(int i = 0; i < t; ++i){
                int x =Integer.parseInt(br.readLine());
                int d1 = distS[g] + distG[h] + distH[x];
                int d2 = distS[h] + distH[g] + distG[x];
                if (distS[x] == d1 || distS[x] == d2)
                    candi[cnt++] = x;
            }
            Arrays.sort(candi, 0, cnt);
            for(int i = 0; i < cnt; ++i)
                sb.append(candi[i]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static int[] dijkstra(List<Node>[] list, int n, int start, int[] dist) {
        Arrays.fill(dist, 0, n + 1, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.vertex] < cur.weight)
                continue;
            int w;
            for(Node next : list[cur.vertex]){
                w = cur.weight + next.weight;
                if(dist[next.vertex] > w){
                    dist[next.vertex] = w;
                    pq.add(new Node(next.vertex, w));
                }
            }
        }
        return dist;
    }
}