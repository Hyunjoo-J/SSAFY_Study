package MST.p16398_행성연결;

import java.io.*;
import java.util.*;

public class p16398_HJ {

	static int N, parents[];
    static long ans;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static class Edge implements Comparable<Edge>{

        int from, to, cost;

        public Edge(int from, int to, int cost) {
            super();
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                int cost = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(i,j, cost));
            }
        }

        Collections.sort(edgeList);

        make();

        int cnt = 0;

        for(Edge edge : edgeList) {
            if(union(edge.from, edge.to)) {
                ans += edge.cost;
                if(++cnt==N-1) break;
            }
        }

        System.out.println(ans);

    }
    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot==bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    private static int findSet(int v) {
        if(parents[v]==v) return v;
        return parents[v] = findSet(parents[v]);
    }
    private static void make() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }
}
