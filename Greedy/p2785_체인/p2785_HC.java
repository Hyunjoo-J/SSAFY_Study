package Greedy.p2785_체인;

import java.io.*;
import java.util.*;

public class p2785_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] L = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(L);

		int left = 0;
		int right = N - 1;
		int answer = 0;
		while (left < right) {
			while (left < right && L[left]-- > 0) {
				++answer;
				--right;
			}
			++left;
		}
		System.out.println(answer);
	    br.close();
	}
}
