package DynamicProgramming.p2533_사회망서비스;// https://www.acmicpc.net/problem/2533

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p2533_HC {

    private static int N, answer = 0;
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        int u, v;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(1);
        System.out.println(answer);
        br.close();
    }

    private static boolean dfs(int curr) {
        visited[curr] = true;
        if (graph.get(curr).size() == 0)
            return false;

        boolean flag = true;
        for (int next: graph.get(curr)) {
            if (visited[next])
                continue;
            flag &= dfs(next);
        }

        if (flag) {
            return false;
        } else {
            ++answer;
            return true;
        }
    }
}

class p2533_HC_SolutionDP {

    private static int N;
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; ++i)
            graph.add(new ArrayList<>());

        int u, v;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
        br.close();
    }

    private static void dfs(int curr) {
        visited[curr] = true;
        dp[curr][0] = 0;
        dp[curr][1] = 1;

        for (int next: graph.get(curr)) {
            if (visited[next])
                continue;
            dfs(next);
            dp[curr][0] += dp[next][1];
            dp[curr][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}
