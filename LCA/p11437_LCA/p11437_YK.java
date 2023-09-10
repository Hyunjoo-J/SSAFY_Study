package LCA.p11437_LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p11437_YK {

    static int N;
    static int[] depth, parents;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        parents = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        int a, b;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        parents[1] = 1;
        depth[1] = 1;
        getDepth(1, 1);

//        System.out.println(Arrays.toString(parents));
//        System.out.println(Arrays.toString(depth));

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (depth[a] > depth[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            while (depth[a] != depth[b]) {
                b = parents[b];
            }

            while (a != b) {
                a = parents[a];
                b = parents[b];
            }

            bw.write(a + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void getDepth(int n, int d) {
        for (int c : graph.get(n)) {
            if (parents[c] != 0) continue;
            parents[c] = n;
            depth[c] = d + 1;
            getDepth(c, d + 1);
        }
    }
}
