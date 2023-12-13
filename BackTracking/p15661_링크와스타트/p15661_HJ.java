package BackTracking.p15661_링크와스타트;

import java.util.*;
import java.io.*;

public class p15661_HJ {
	static int N;
	static int ans = Integer.MAX_VALUE;
	static int[][] player;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //사람의 수
		player = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int dep, int bit) {
		if ((bit & (-bit)) > 0) {
			int start = 0;
			int link = 0;
			for (int i = 0; i < N; ++i) {
				if ((bit & (1 << i)) > 0) {
					for (int j = i + 1; j < N; ++j) {
						if ((bit & (1 << j)) > 0) {
							start += player[i][j];
							start += player[j][i];
						}
					}
				} else {
					for (int j = i + 1; j < N; ++j) {
						if ((bit & (1 << j)) == 0) {
							link += player[i][j];
							link += player[j][i];
						}
					}
				}
			}
			ans = Math.min(ans, Math.abs(start - link));
		}
		for (int i = dep; i < N; ++i) {
			dfs(i + 1, bit | (1 << i));
		}
	}
}
