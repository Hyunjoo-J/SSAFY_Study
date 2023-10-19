package DynamicProgramming.p2293_동전1;

import java.io.*;
import java.util.*;

public class p2293_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		int[][] dp = new int[n][k + 1];
		for (int i = 0; i < n; ++i) {
			coins[i] = Integer.parseInt(br.readLine());
			if (coins[i] <= k)
				dp[i][coins[i]] = 1;
		}

		for (int j = 0; j <= k; ++j) {
			for (int i = 0; i < n; ++i) {
				if (j >= coins[i]) {
					for (int l = 0; l <= i; ++l) {
						dp[i][j] += dp[l][j - coins[i]];
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; ++i)
			answer += dp[i][k];

		System.out.println(answer);
		br.close();
	}
}
