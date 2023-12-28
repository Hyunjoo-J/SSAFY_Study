package DynamicProgramming.P11066_파일합치기;

import java.io.*;
import java.util.*;

public class p11066_HC {

	private static final int INF = Integer.MAX_VALUE;
	private static final int[] cost = new int[500];
	private static final int[] prefixSum = new int[501];
	private static final int[][] cache = new int[500][500];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			// init
			Arrays.fill(prefixSum, 0, k, 0);
			for (int i = 0; i < k; ++i) {
				Arrays.fill(cache[i], 0, k, -1);
			}

			// cost
			for (int i = 0; i < k; ++i) {
				cost[i] = Integer.parseInt(st.nextToken());
			}

			// prefix sum
			for (int i = 1; i <= k; ++i) {
				prefixSum[i] = prefixSum[i - 1] + cost[i - 1];
			}

			// answer
			sb.append(dp(0, k - 1) - prefixSum[k]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int dp(int s, int e) {
		if (s == e)
			return cost[s];
		if (cache[s][e] != -1)
			return cache[s][e];

		int sum = prefixSum[e + 1] - prefixSum[s];

		cache[s][e] = INF;
		for (int i = s; i < e; ++i) {
			cache[s][e] = Math.min(cache[s][e], dp(s, i) + dp(i + 1, e) + sum);
		}
		return cache[s][e];
	}
}
