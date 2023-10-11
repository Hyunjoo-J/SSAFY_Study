package TwoPointer.p20922_겹치는건싫어;

import java.util.*;
import java.io.*;

public class p20922_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, num[], numCount[], max = 1;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		num = new int[N];
		numCount = new int[100001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int i = 0, j = 1;
		numCount[num[0]]++;

		while (true) {
			if (j == N) {
				max = Math.max(max, j - i);
				break;
			}

			if (++numCount[num[j]] <= K)
				j++;
			else {
				max = Math.max(max, j - i);
				while (numCount[num[j]] > K) {
					numCount[num[i]]--;
					i++;
				}
				numCount[num[j]]--;
			}
		}

		System.out.println(max);
	}
}
