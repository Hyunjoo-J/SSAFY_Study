package DynamicProgramming.p9095_123더하기;

import java.io.*;

public class p9095_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		final int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i < 11; ++i) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			bw.write(dp[Integer.parseInt(br.readLine())] + "\n");
		}
	    bw.flush();
	    bw.close();
	    br.close();
	}
}
