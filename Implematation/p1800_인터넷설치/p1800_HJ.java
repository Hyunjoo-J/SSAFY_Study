package Implematation.p1800_인터넷설치;

import java.util.*;
import java.io.*;
public class p1800_HJ {
    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int N, P, K;
    static List<Node>[] list;
    static int[] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; ++i){
            list[i] = new ArrayList<>();
        }
        dist = new int[N + 1]; //원하는 가중치보다 넘치는 게 몇 개인지
        int max = 0;
        for(int i = 0; i < P; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
            list[v].add(new Node(u, w));
            max = Math.max(max, w);
        }
        int left = 0;
        int right = max;
        int ans = Integer.MIN_VALUE;
        //가중치 이분 탐색으로 언제 가능한지 확인
        while(left <= right){
            int mid = (left + right)/2;
            if(dijkstra(mid)){
                ans = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(ans == Integer.MIN_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(ans);
        }
    }
    private static boolean dijkstra(int mid){
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int v = cur.to;
            int w = cur.weight;
            if(dist[v] < w){
                continue;
            }
            for(Node tmp : list[v]){
                int nv = tmp.to;
                int nw = w;
                if(tmp.weight > mid){
                    ++nw;
                }
                if(nw < dist[nv]){
                    dist[nv] = nw;
                    pq.add(new Node(nv, dist[nv]));
                }
            }
        }
        return dist[N] <= K;
    }
}