package TwoPointer.p20922_겹치는건싫어;

import java.io.*;
import java.util.*;

public class p20922_HC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] counter = new int[100001];
		int[] a = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int left = 0;
		for (int right = 0; right < N; ++right) {
			++counter[a[right]];
			while (counter[a[right]] > K) {
				--counter[a[left++]];
			}
			answer = Math.max(answer, right - left + 1);
		}
		System.out.println(answer);
		br.close();
	}
}