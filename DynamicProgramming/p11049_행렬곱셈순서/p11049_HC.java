package DynamicProgramming.p11049_행렬곱셈순서;

import java.io.*;
import java.util.*;

public class p11049_HC {

	private static Size[] matrices;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		matrices = new Size[N];
		dp = new int[N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			matrices[i] = new Size(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		System.out.println(dp(0, N - 1));
		br.close();
	}

	private static int dp(int s, int e) {
		if (s >= e)
			return 0;
		if (dp[s][e] != 0)
			return dp[s][e];

		dp[s][e] = Integer.MAX_VALUE;
		for (int k = s; k < e; ++k) {
			dp[s][e] = Math.min(dp[s][e],
				dp(s, k) + (matrices[s].r * matrices[k].c * matrices[e].c) + dp(k + 1, e)
			);
		}
		return dp[s][e];
	}

	private static class Size {
		int r, c;

		public Size(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}