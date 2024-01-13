package MST.p23743_방탈출;

import java.io.*;
import java.util.*;

public class p23743_YK {

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static void init() {
        for (int i = 0; i <= N; ++i) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (aRoot < bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
        return true;
    }

    static int[] parents;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0, a, b, c; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            pq.offer(new Edge(0, i, Integer.parseInt(st.nextToken())));
        }

        parents = new int[N + 1];
        init();
        int result = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (!union(now.from, now.to)) continue;
            result += now.weight;
        }

        System.out.println(result);
        br.close();
    }
}
