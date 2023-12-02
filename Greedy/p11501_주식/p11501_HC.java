package Greedy.p11501_주식;

import java.io.*;
import java.util.*;

public class p11501_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int[] arr = new int[1000001];

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			long answer = 0;
			int top = Integer.MIN_VALUE;
			for (int i = N - 1; i >= 0; --i) {
				top = Math.max(top, arr[i]);
				answer += top - arr[i];
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}
}
