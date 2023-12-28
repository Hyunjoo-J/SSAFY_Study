package DynamicProgramming.p11066_파일합치기;
import java.util.*;
import java.io.*;

public class p11066_HJ {
	static int[] sum = new int[501];
	static int[][] dp = new int[501][501];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt((br.readLine()));
		StringTokenizer st;
		while (T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; ++i) {
				sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
			}
			merge(K);
			sb.append(dp[1][K]).append("\n");
		}
		System.out.print(sb);
	}

	public static void merge(int K) {
		for (int n = 1; n <= K; n++) {
			for (int i = 1; i + n <= K; i++) {
				int j = i + n;
				dp[i][j] = Integer.MAX_VALUE;
				for (int x = i; x < j; x++) {
					dp[i][j] = Math.min(dp[i][j],
							dp[i][x] + dp[x + 1][j] + sum[j] - sum[i - 1]);
				}
			}
		}
	}
}
