package BackTracking.p15661_링크와스타트;

import java.io.*;
import java.util.*;

/**
 * Combination + Simulation
 * 메모리	12496KB
 * 시간		896ms
 */
public class p15661_HC {

	private static int N;
	private static int[][] S;
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(answer);
		br.close();
	}

	private static void dfs(int idx, int visit) {
		if ((visit & (-visit)) > 0) {
			int start = 0;
			int link = 0;
			for (int i = 0; i < N; ++i) {
				if ((visit & (1 << i)) > 0) {
					for (int j = i + 1; j < N; ++j) {
						if ((visit & (1 << j)) > 0) {
							start += S[i][j];
							start += S[j][i];
						}
					}
				} else {
					for (int j = i + 1; j < N; ++j) {
						if ((visit & (1 << j)) == 0) {
							link += S[i][j];
							link += S[j][i];
						}
					}
				}
			}
			answer = Math.min(answer, Math.abs(start - link));
		}

		for (int i = idx; i < N; ++i) {
			dfs(i + 1, visit | (1 << i));
		}
	}
}

/**
 * Subset + Summation Array
 * 메모리	11808KB
 * 시간		92ms
 */
class p15661_HC_Solution2 {

	private static int N, N2;
	private static int[] sumRow;
	private static int[] sumColumn;
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		N2 = N >> 1;
		sumRow = new int[N];
		sumColumn = new int[N];

		int sumAll = 0;
		for (int i = 0, S; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				S = Integer.parseInt(st.nextToken());
				sumRow[i] += S;
				sumColumn[j] += S;
				sumAll += S;
			}
		}
		dfs(0, 0, sumAll);
		System.out.println(answer);
	    br.close();
	}

	private static void dfs(int idx, int cnt, int sum) {
		if (idx == N || cnt == N2) {
			answer = Math.min(answer, Math.abs(sum));
			return;
		}
		dfs(idx + 1, cnt + 1, sum - sumRow[idx] - sumColumn[idx]);
		dfs(idx + 1, cnt, sum);
	}
}
