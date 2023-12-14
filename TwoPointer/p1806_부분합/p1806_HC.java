package TwoPointer.p1806_부분합;

import java.io.*;
import java.util.*;

public class p1806_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long summation = 0;
		int left = 0;
		int answer = Integer.MAX_VALUE;
		for (int right = 0; right < N; ++right) {
			summation += arr[right];

			while (summation >= S) {
				answer = Math.min(answer, right - left + 1);
				summation -= arr[left];
				++left;
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
	    br.close();
	}
}
