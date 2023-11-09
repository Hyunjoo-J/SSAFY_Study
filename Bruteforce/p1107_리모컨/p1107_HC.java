package Bruteforce.p1107_리모컨;

import java.io.*;
import java.util.*;

public class p1107_HC {

	private static final int INF = Integer.MAX_VALUE >> 2;
	private static int btn = 0;
	private static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		String input = br.readLine();
		int len = input.length();
		N = Integer.parseInt(input);
		int M = Integer.parseInt(br.readLine());

		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i) {
				int b = Integer.parseInt(st.nextToken());
				btn |= (1 << b);
			}
		}

		int answer = Math.abs(100 - N);
		if ((btn & 1) == 0)
			answer = Math.min(answer, N + 1);

		int start = Math.max(len - 1, 1);
		int end = Math.min(len + 1, 6);
		for (int i = start; i <= end; ++i) {
			answer = Math.min(answer, i + find(i, 0));
		}
		System.out.println(answer);
	    br.close();
	}

	private static int find(int n, int curr) {
		if (n == 0) {
			return Math.abs(curr - N);
		}
		int res = INF;
		for (int i = 0; i <= 9; ++i) {
			if (((1 << i) & btn) > 0)
				continue;
			if (curr == 0 && i == 0)
				continue;
			res = Math.min(res, find(n - 1, curr * 10 + i));
		}
		return res;
	}
}
