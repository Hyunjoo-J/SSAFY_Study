package DynamicProgramming.p2302_극장좌석;

import java.io.*;
import java.util.*;

public class p2302_HC {

	private static int N;
	private static boolean[] isVip = new boolean[41];
	private static int[] dp = new int[41];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; ++i) {
			isVip[Integer.parseInt(br.readLine())] = true;
		}
		Arrays.fill(dp, -1);
		System.out.println(dfs(1));
	    br.close();
	}

	private static int dfs(int idx) {
		if (idx > N)
			return 1;
		if (dp[idx] != -1)
			return dp[idx];
		if (isVip[idx])
			return dp[idx] = dfs(idx + 1);

		dp[idx] = 0;
		if (idx < N && !isVip[idx + 1]) {
			dp[idx] += dfs(idx + 2);
		}
		dp[idx] += dfs(idx + 1);
		return dp[idx];
	}
}
