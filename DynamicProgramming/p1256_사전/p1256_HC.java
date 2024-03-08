package DynamicProgramming.p1256_사전;

import java.io.*;
import java.util.*;

/**
 * dp + 조합론
 */
public class p1256_HC {

	private static final int MAX = (int) 1e9;
	private static int[][] cache;
	private static boolean find;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		cache = new int[N + M + 1][N + M + 1];

		StringBuilder sb = new StringBuilder();
		dfs(N, M, K, sb);
		System.out.println(find ? sb.toString() : -1);
		br.close();
	}

	private static void dfs(int a, int z, int k, StringBuilder sb) {
		if (a + z == 0) {
			find = true;
			return;
		}
		int combination = comb(a + z - 1, a - 1);
		if (a > 0 && combination >= k) {
			sb.append("a");
			dfs(a - 1, z, k, sb);
		} else if (z > 0) {
			sb.append("z");
			dfs(a, z - 1, k - combination, sb);
		}
	}

	private static int comb(int n, int k) {
		if (n <= 0 || k <= 0 || n == k)
			return 1;
		if (cache[n][k] > 0)
			return cache[n][k];
		return cache[n][k] = Math.min(comb(n - 1, k) + comb(n - 1, k - 1), MAX);	// ***
	}
}
