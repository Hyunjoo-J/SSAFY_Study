package LCA.p11438_LCA2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11438_YK {
    static int N;
    static int[][] parents;
    static int[] depth;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1][18];
        depth = new int[N + 1];
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

        Arrays.fill(parents[1], 1);
        depth[1] = 1;
        getDepth(1, 1);
        fillParents();

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

            for (int j = 17; j >= 0; --j) {
                if (depth[a] == depth[b]) break;
                if (depth[b] - Math.pow(2, j) >= depth[a]) {
                    b = parents[b][j];
                }
            }

            if (a == b) bw.write(a + "\n");
            else {
                for (int j = 17; j >= 0; --j) {
                    if (parents[a][j] == parents[b][j]) continue;
                    a = parents[a][j];
                    b = parents[b][j];
                }
                bw.write(parents[a][0] + "\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void fillParents() {
        for (int i = 1; i <= 17; ++i) {
            for (int j = 1; j <= N; ++j) {
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
            }
        }
    }
    private static void getDepth(int n, int d) {
        for (int c : graph.get(n)) {
            if (depth[c] != 0) continue;
            depth[c] = d + 1;
            parents[c][0] = n;
            getDepth(c, d + 1);
        }
    }
}
