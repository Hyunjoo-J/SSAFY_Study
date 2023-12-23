package TopologySort.p1005_ACMCraft;

import java.io.*;
import java.util.*;

public class p1005_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, W;
        int[] D = new int[1001];
        int[] inDegree = new int[1001];
        int[] completedTime = new int[1001];
        List<List<Integer>> graph = new ArrayList<>(1001);
        for (int i = 0; i < 1001; ++i) {
            graph.add(new ArrayList<>());
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // test case
        while (T-- > 0) {

            // input
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            Arrays.fill(inDegree, 0, N + 1, 0);
            Arrays.fill(completedTime, 0, N + 1, 0);
            for (int i = 0; i <= N; ++i) {
                graph.get(i).clear();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; ++i) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0, X, Y; i < K; ++i) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                graph.get(X).add(Y);
                ++inDegree[Y];
            }
            W = Integer.parseInt(br.readLine());

            // topology sort
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= N; ++i) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    completedTime[i] = D[i];
                }
            }

            for (int i = 0; i < N; ++i) {
                int now = queue.remove();

                for (int next: graph.get(now)) {
                    completedTime[next] = Math.max(completedTime[next], completedTime[now] + D[next]);
                    if (--inDegree[next] == 0)
                        queue.add(next);
                }
            }
            sb.append(completedTime[W]).append("\n");
        }

        // answer
        System.out.println(sb.toString());
        br.close();
    }
}
