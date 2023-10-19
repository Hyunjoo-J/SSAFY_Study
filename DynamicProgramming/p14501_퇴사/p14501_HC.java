package DynamicProgramming.p14501_퇴사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14501_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];
		for (int i = 0; i < N; ++i) {
			if (i > 0)
				dp[i] = Math.max(dp[i], dp[i - 1]);
			if (i + T[i] <= N)
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
		}
		dp[N] = Math.max(dp[N], dp[N - 1]);
		System.out.println(dp[N]);
		br.close();
	}
}
