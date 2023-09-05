package MST.p16398_행성연결;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p16398_YK {
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

    static int N;
    static int[] parents;

    private static void make() {
        parents = new int[N];
        for (int i = 0; i < N; ++i) {
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Edge[] edges = new Edge[N * N - N];
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                if (i == j) {
                    st.nextToken();
                    continue;
                }
                edges[cnt++] = new Edge(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        make();
        Arrays.sort(edges);
        long result = 0;
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
