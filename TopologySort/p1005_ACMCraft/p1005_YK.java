package TopologySort.p1005_ACMCraft;

import java.io.*;
import java.util.*;
public class p1005_YK {

    static int[] times = new int[1000];
    static int[] endTimes = new int[1000];
    static int[] indegree = new int[1000];
    static List<Integer>[] list = new ArrayList[1000];
    static int N, K, W;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            Arrays.fill(indegree, 0);

            for (int i = 0; i < N; ++i) {
                list[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                times[i] = Integer.parseInt(st.nextToken());
                endTimes[i] = times[i];
            }

            for (int i = 0; i < K; ++i) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                ++indegree[to];
                list[from].add(to);
            }

            W = Integer.parseInt(br.readLine()) - 1;
            sb.append(topologySort()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int topologySort() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; ++i) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == W) return endTimes[W];

            for (int k : list[now]) {
                endTimes[k] = Math.max(endTimes[k], endTimes[now] + times[k]);
                if (--indegree[k] == 0) q.offer(k);
            }
        }

        return -1;
    }
}
