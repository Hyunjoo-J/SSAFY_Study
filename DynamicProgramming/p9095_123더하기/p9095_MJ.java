package DynamicProgramming.p9095_123더하기;

import java.util.*;
import java.io.*;

public class p9095_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		int dp[] = new int[11];
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;

		for(int i=3; i<=10; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}

		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(dp[num]);
		}
		
		br.close();
	}
}
