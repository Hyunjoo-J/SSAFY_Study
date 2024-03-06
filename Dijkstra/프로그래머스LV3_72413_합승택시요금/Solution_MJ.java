package Dijkstra.프로그래머스LV3_72413_합승택시요금;

import java.util.*;

class Solution_MJ {
    static class Node implements Comparable<Node>{
        int vertex, cost;
        
        public Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost);
        }
    }
    
    static List<Node>[] list;
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
                
        list = new ArrayList[n+1];
        for(int i=1; i<=n; ++i){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<fares.length; ++i){
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            int cost = fares[i][2];
            
            list[v1].add(new Node(v2, cost));
            list[v2].add(new Node(v1, cost));
        }
        
        int[] distA = dijkstra(a, n);
        int[] distB = dijkstra(b, n);
        int[] distS = dijkstra(s, n);
        int[] ans = new int[n+1];
        
        for(int i=1; i<=n; ++i){
            int cost = distA[i] + distB[i] + distS[i];
            answer = Math.min(cost, answer);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int start, int n){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        Queue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!heap.isEmpty()){
            Node cur = heap.poll();
            
            if(dist[cur.vertex] < cur.cost)
                continue;
            
            for(Node next : list[cur.vertex]){
                int cost = cur.cost + next.cost;
                
                if(dist[next.vertex] > cost){
                    dist[next.vertex] = cost;
                    heap.add(new Node(next.vertex, cost));
                }
            }
        }
        
        return dist;
    }
}
