package DynamicProgramming.p11060_점프점프;

import java.util.*;
import java.io.*;

public class p11060_HJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] maze = new int[N];
		int[] dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			maze[i] = Integer.parseInt(st.nextToken());
			dp[i] = Integer.MAX_VALUE;
		}
		dp[0] = 0;
		for(int i = 0; i < N - 1; ++i) {
			if(dp[i] == Integer.MAX_VALUE)
				break;
			for(int j = 1, size = maze[i]; j <= size && i + j < N; ++j) {
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}
		if(dp[N - 1] == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(dp[N - 1]);

	}

}