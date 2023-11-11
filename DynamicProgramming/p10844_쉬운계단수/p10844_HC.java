package DynamicProgramming.p10844_쉬운계단수;

import java.io.*;

public class p10844_HC {

	private static final int P = 1_000_000_000;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[10];
		int[] temp = new int[10];

		for (int j = 1; j < 10; ++j) {
			dp[j] = 1;
		}
		for (int i = 2; i <= N; ++i) {
			temp[0] = dp[1];
			for (int j = 1; j < 9; ++j) {
				temp[j] = (dp[j - 1] + dp[j + 1]) % P;
			}
			temp[9] = dp[8];

			int[] temp2 = dp;
			dp = temp;
			temp = temp2;
		}

		int answer = 0;
		for (int num: dp) {
			answer = (answer + num) % P;
		}
		System.out.println(answer);
	    br.close();
	}
}
