package DynamicProgramming.p17404_RGB거리2;

import java.io.*;
import java.util.*;

public class P17404_HC {

	private static final int INF = Integer.MAX_VALUE >> 2;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
		}
		System.out.println(Math.min(Math.min(dp(rgb, N, 0), dp(rgb, N, 1)), dp(rgb, N, 2)));
	    br.close();
	}

	private static int dp(int[][] rgb, int n, int color) {
		int[] dp = new int[3];
		Arrays.fill(dp, INF);
		dp[color] = rgb[0][color];
		int red, green, blue;
		for (int i = 1; i < n; ++i) {
			red = dp[0];
			green = dp[1];
			blue = dp[2];
			dp[0] = Math.min(green, blue) + rgb[i][0];
			dp[1] = Math.min(red, blue) + rgb[i][1];
			dp[2] = Math.min(red, green) + rgb[i][2];
		}
		if (color == 0)
			return Math.min(dp[1], dp[2]);
		else if (color == 1)
			return Math.min(dp[0], dp[2]);
		else
			return Math.min(dp[0], dp[1]);
	}
}
