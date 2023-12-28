package TopologySort.p1005_ACMCraft;

import java.util.*;
import java.io.*;

public class p1005_HJ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, K, W;
        int[] D = new int[1001];
        int[] degree = new int[1001];
        int[] time = new int[1001];
        ArrayList<Integer>[] list = new ArrayList[1001];
        for (int i = 0; i < 1001; ++i)
            list[i] = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            Arrays.fill(degree, 0, N + 1, 0);
            Arrays.fill(time, 0, N + 1, 0);
            for (int i = 0; i <= N; ++i)
                list[i].clear();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; ++i)
                D[i] = Integer.parseInt(st.nextToken());
            for(int i = 0, X, Y; i < K; ++i){
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                list[X].add(Y);
                ++degree[Y];
            }
            W = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for(int i = 1; i <= N; ++i){
                if(degree[i] == 0){
                    q.add(i);
                    time[i] = D[i];
                }
            }

            for(int i = 0; i < N; ++i){
                int cur = q.poll();
                for(int next : list[cur]){
                    time[next] = Math.max(time[next], time[cur] + D[next]);
                    if(--degree[next] == 0)
                        q.add(next);
                }
            }
            sb.append(time[W]).append("\n");
        }
        System.out.println(sb);
    }
}
