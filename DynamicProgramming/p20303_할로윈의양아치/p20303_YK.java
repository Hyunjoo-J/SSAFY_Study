package DynamicProgramming.p20303_할로윈의양아치;

import java.io.*;
import java.util.*;

public class p20303_YK {

    static int N, M, K;
    static int[] parents, candies;
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    static ArrayList<int[]> list;

    private static void init() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            parents[i] = i;
        }
    }

    private static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        if (aRoot > bRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        parents[bRoot] = aRoot;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        init();
        adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; ++i) {
        	adjList[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
            adjList[a].add(b);
            adjList[b].add(a);
        }

        visited = new boolean[N + 1];
        list = new ArrayList<>();
        list.add(new int[] {0, 0});
        for (int i = 1; i <= N; ++i) {
            if (visited[i]) continue;
            visited[i] = true;
            bfs(i);
        }

        int size = list.size();
        int[][] dp = new int[size][K];
        // w : 친구 수, v : 사탕 수
        for (int i = 1; i < size; ++i) {
            for (int j = 1; j < K; ++j) {
                int w = list.get(i)[0];
                int v = list.get(i)[1];
                if (j < w) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
            }
        }

        bw.write(dp[size - 1][K - 1] + "");
        bw.flush();
        bw.close();
        br.close();
    }

	private static void bfs(int i) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(i);
		
		int cnt = 0, candy = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			cnt += 1;
			candy += candies[cur];
			
			for (int next : adjList[cur]) {
				if (visited[next]) continue;
				visited[next] = true;
				q.offer(next);
			}
		}
		
		list.add(new int[] {cnt, candy});
	}
}