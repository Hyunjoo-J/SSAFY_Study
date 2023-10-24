package DynamicProgramming.p9252_LCS2;

import java.io.*;

public class p9252_HJ {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line1 = br.readLine();
		String line2 = br.readLine();
		int N = line1.length();
		int M = line2.length();
		int[][] dp = new int[N + 1][M + 1];
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(line1.charAt(i - 1) == line2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		System.out.println(dp[N][M]);
		if(dp[N][M] == 0)
			return;
		StringBuilder sb = new StringBuilder();
		int i = N, j = M;
		while (i > 0 && j > 0) {
			if(line1.charAt(i - 1) == line2.charAt(j - 1)) {
				sb.append(line1.charAt(i - 1));
				--i;
				--j;
			}else {
				if(dp[i - 1][j] > dp[i][j - 1])
					--i;
				else
					--j;
			}
		}
		sb.reverse();
		System.out.println(sb.toString());
	}

}