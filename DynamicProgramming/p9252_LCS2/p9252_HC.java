package DynamicProgramming.p9252_LCS2;

import java.io.*;

public class p9252_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String t = br.readLine();
		int n = s.length();
		int m = t.length();

		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (s.charAt(i - 1) == t.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		if (dp[n][m] == 0) {
			System.out.println(dp[n][m]);
		} else {
			int i = n;
			int j = m;
			StringBuilder sb = new StringBuilder();
			while (i > 0 && j > 0) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					sb.append(s.charAt(i - 1));
					--i;
					--j;
				} else {
					if (dp[i - 1][j] > dp[i][j - 1])
						--i;
					else
						--j;
				}
			}
			sb.reverse();
			System.out.println(dp[n][m]);
			System.out.println(sb.toString());
		}
		br.close();
	}
}
