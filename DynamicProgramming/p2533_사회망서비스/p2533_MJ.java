package DynamicProgramming.p2533_사회망서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p2533_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static ArrayList<Integer> list[];
	static int dp[][];
	static boolean visited[];

	private static void dfs(int parent) {
		visited[parent] = true;
		
		dp[parent][0] = 0;
		dp[parent][1] = 1;
				
		for(int i=0; i<list[parent].size(); i++) {			
			int child = list[parent].get(i);
			
			// 부모일 경우
			if(visited[list[parent].get(i)]) continue;
			
			dfs(child);
			dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
			dp[parent][0] += dp[child][1];
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[1000001][2];
		visited = new boolean[N+1];

		list = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
		
			list[start].add(end);
			list[end].add(start);
		}
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
		
		br.close();
	}
}
