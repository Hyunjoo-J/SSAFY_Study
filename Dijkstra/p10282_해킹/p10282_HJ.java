package Dijkstra.p10282_해킹;

import java.io.*;
import java.util.*;

public class p10282_HJ {
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
    static int T, N, D, C, cnt;
    static Node cur;
    static int[] dist;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            list = new ArrayList[N + 1];
            for(int i = 0 ; i <= N; ++i)
                list[i] = new ArrayList<Node>();
            for(int i = 0; i < D; ++i){
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                list[u].add(new Node(v, w));
            }
            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            cnt = 0;
            dijkstra();
            int max = 0;
            for(int i = 1; i <= N; ++i){
                if(dist[i] != Integer.MAX_VALUE)
                    max = Math.max(max, dist[i]);
            }
            sb.append(cnt+" "+max+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    private static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Node(C, 0));
        dist[C] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.v])
                continue;
            visited[cur.v] = true;
            ++cnt;
            for(Node node : list[cur.v]){
                if(dist[node.v] > dist[cur.v] + node.weight){
                    dist[node.v] = dist[cur.v] + node.weight;
                    pq.offer(new Node(node.v, dist[node.v]));
                }
            }
        }
    }
}
