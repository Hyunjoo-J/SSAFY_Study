package BFS_DFS.p1939_중량제한;

import java.util.*;
import java.io.*;

public class p1939_HJ {
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
    static List<Node>[] edge;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edge = new ArrayList[N + 1];
        for(int i = 1; i <= N; ++i)
            edge[i] = new ArrayList<>();
        int right = 0;
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[u].add(new Node(v, w));
            edge[v].add(new Node(u, w));
            right = Math.max(w, right);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int left = 0;
        int ans = 0;
        while(left <= right){
            int mid = (left + right) >> 1;
            visited = new boolean[N + 1];
            if(bfs(mid)){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean bfs(int mid) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(S);
        while(!que.isEmpty()){
            int cur = que.poll();
            visited[cur] = true;
            if(cur == E)
                return true;
            for(Node tmp : edge[cur]){
                if(tmp.w >= mid && !visited[tmp.v]){
                    visited[tmp.v] = true;
                    que.offer(tmp.v);
                }
            }
        }
        return false;
    }
}
