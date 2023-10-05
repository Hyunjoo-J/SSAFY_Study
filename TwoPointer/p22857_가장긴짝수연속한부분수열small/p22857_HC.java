package TwoPointer.p22857_가장긴짝수연속한부분수열small;

import java.io.*;
import java.util.*;

public class p22857_HC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] S = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int odd = 0;
		int left = 0;
		for (int right = 0; right < N; ++right) {
			if ((S[right] & 1) == 1)
				++odd;

			while (odd > K)
				odd -= S[left++] & 1;

			answer = Math.max(answer, right - left + 1 - odd);
		}

		System.out.println(answer);
		br.close();
	}
}