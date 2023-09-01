package Dijkstra.p11779_최소비용구하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p11779_HJ {
    static class Node implements Comparable<Node>{
        int v, w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static int N, M, S, E;
    static int[] dist, path;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        path = new int[N + 1];
        for(int i = 1; i <= N; ++i)
            list[i] = new ArrayList<>();
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();
        int road = E;
        Stack<Integer> stack = new Stack<>();
        while(road != 0){
            stack.push(road);
            road = path[road];
        }
        sb.append(dist[E]+"\n"+stack.size()+"\n");
        while(!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);
    }
    private static void dijkstra(){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(S, 0));
        boolean[] visited = new boolean[N + 1];
        dist[S] = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(visited[cur.v])
                continue;
            visited[cur.v] = true;
            for(Node node : list[cur.v]){
                if(dist[node.v] > dist[cur.v] + node.w){
                    dist[node.v] = dist[cur.v] + node.w;
                    q.offer(new Node(node.v, dist[node.v]));
                    path[node.v] = cur.v;
                }
            }
        }
    }
}
