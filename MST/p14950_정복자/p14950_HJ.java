package MST.p14950_정복자;

import java.io.*;
import java.util.*;

public class p14950_HJ {
static class Node implements Comparable<Node>{
        int u, v, weight;

        public Node(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int N, M, t;
    static Node[] node;
    static int[] parents;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        node = new Node[M];
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node[i] = new Node(u, v, w);
        }
        Arrays.sort(node);
        make();
        int res = 0, cnt = 0;
        for(int i = 0; i < M; ++i){
            if(union(node[i].u, node[i].v)){
                res += node[i].weight;
                res += t * cnt;
                if(++cnt == N - 1)
                    break;
            }
        }
        System.out.println(res);
    }
    private static int find(int v){
        if(parents[v] == v)
            return v;
        return parents[v] = find(parents[v]);
    }
    private static boolean union(int a, int b) {
        int ar = find(a);
        int br = find(b);
        if(ar == br)
            return false;
        parents[br] = ar;
        return true;
    }
    private static void make() {
        parents = new int[N + 1];
        for(int i = 1; i <= N; ++i)
            parents[i] = i;
    }
}
