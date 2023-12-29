package Greedy.p20300_서강근육맨;

import java.io.*;
import java.util.*;

public class p20300_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long[] t = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			t[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(t);

		long result = 0;
		int right = N - 1;
		if ((N & 1) == 1) {
			result = t[right--];
		}

		for (int left = 0; left < right; ++left, --right) {
			result = Math.max(result, t[left] + t[right]);
		}
		System.out.println(result);
	    br.close();
	}
}
