package DynamicProgramming.p11049_행렬곱셈순서;

import java.util.*;
import java.io.*;
public class p11049_HJ {
	static int[][] matrix;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		matrix = new int[N][2];
		dp = new int[N][N];
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dp(0, N - 1));
	}
	private static int dp(int S, int E){
		if(S >= E)
			return 0;
		if(dp[S][E] != 0)
			return dp[S][E];

		dp[S][E] = Integer.MAX_VALUE;
		for(int i = S; i < E; ++i){
			dp[S][E] = Math.min(dp[S][E], dp(S, i) + (matrix[S][0] * matrix[i][1] * matrix[E][1]) + dp(i + 1, E));
		}
		return dp[S][E];
	}
}
