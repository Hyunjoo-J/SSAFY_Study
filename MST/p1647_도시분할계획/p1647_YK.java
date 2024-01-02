package MST.p1647_도시분할계획;

import java.io.*;
import java.util.*;
public class p1647_YK {

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

    static int[] parents;
    static int N, M;

    private static void init() {
        for (int i = 1; i < N + 1; ++i) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }

        init();
        int result = 0;
        int cnt = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (union(now.from, now.to)) {
                result += now.weight;
                max = Math.max(max, now.weight);
                if (++cnt == N - 1) break;
            }
        }

        System.out.println(result - max);
        br.close();
    }
}
