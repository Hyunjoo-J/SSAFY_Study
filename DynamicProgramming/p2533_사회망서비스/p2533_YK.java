package DynamicProgramming.p2533_사회망서비스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p2533_YK {
    static int N;
    static LinkedList<Integer>[] tree;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new LinkedList[N + 1];
        dp = new int[N + 1][2];

        for (int i = 0; i < N + 1; ++i) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[v].add(u);
            tree[u].add(v);
        }

        dp(1, new boolean[N + 1]);
        bw.write(String.valueOf(Math.min(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dp(int x, boolean[] visited) {
        visited[x] = true;
        dp[x][1] = 1;
        for (int i = 0, size = tree[x].size(); i < size; ++i) {
            int friend = tree[x].get(i);
            if (visited[friend]) continue;

            dp(friend, visited);

            // 일반인 : 친구가 어답터일 때만
            dp[x][0] += dp[friend][1];

            // 어답터 : 친구가 일반인, 어답터인 경우 중 작은 값
            dp[x][1] += Math.min(dp[friend][0], dp[friend][1]);
        }
    }
}
