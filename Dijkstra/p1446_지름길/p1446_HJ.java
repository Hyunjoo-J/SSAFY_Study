package Dijkstra.p1446_지름길;
import java.io.*;
import java.util.*;
public class p1446_HJ {
    static class Node implements Comparable<Node>{
        int v, weigth;
        public Node(int v, int weigth) {
            super();
            this.v = v;
            this.weigth = weigth;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weigth, o.weigth);
        }

    }
    static int N, D;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //지름길 수
        D = Integer.parseInt(st.nextToken()); //고속도로 길이
        int[] dist = new int[D + 1];
        list = new ArrayList[10001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i = 0; i < 10001; ++i) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(v - u > weight)
                list[v].add(new Node(u, weight));
        }
        dist[0] = 0;
        for(int i = 1 ; i <= D; ++i) {//해당 길이까지의 최소 거리 갱신
            if(list[i].size() > 0) { //도착하는 지름길이 있다면
                for(Node tmp : list[i]) {
                    if(dist[i] >dist[tmp.v] + tmp.weigth) {
                        dist[i] = Math.min(dist[i - 1] + 1, dist[tmp.v] + tmp.weigth);
                    }
                }
                continue;
            }
            dist[i] = dist[i - 1] + 1;
        }
        System.out.println(dist[D]);

    }
}
