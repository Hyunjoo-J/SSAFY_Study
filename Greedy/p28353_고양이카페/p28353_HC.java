package Greedy.p28353_고양이카페;

import java.io.*;
import java.util.*;

public class p28353_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] w = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			w[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(w);

		int answer = 0;
		int left = 0;
		for (int right = N - 1; right > 0 && left < right; --right) {
			if (w[left] + w[right] <= K) {
				++answer;
				++left;
			}
		}
		System.out.println(answer);
	    br.close();
	}
}
