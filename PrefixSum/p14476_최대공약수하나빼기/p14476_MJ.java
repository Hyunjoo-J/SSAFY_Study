package PrefixSum.p14476_최대공약수하나빼기;

import java.util.*;
import java.io.*;

public class p14476_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, num[], left[], right[], max = 0, maxIdx = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		left = new int[N];
		right = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		left[0] = num[0];
		right[N - 1] = num[N - 1];

		for (int i = 1; i < N; i++) {
			left[i] = gcd(left[i - 1], num[i]);
		}

		for (int i = N - 2; i >= 0; i--) {
			right[i] = gcd(right[i + 1], num[i]);
		}

		for (int i = 0; i < N; i++) {
			int tmp = 0;
			if (i == 0) {
				tmp = right[1];
			} else if (i == N - 1) {
				tmp = left[N - 2];
			} else {
				tmp = gcd(left[i - 1], right[i + 1]);
			}

			if (num[i] % tmp != 0 && tmp > max) {
				max = tmp;
				maxIdx = i;
			}
		}

		if (max != 0)
			System.out.println(max + " " + num[maxIdx]);
		else
			System.out.println(-1);

		br.close();
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;

		return gcd(b, a % b);
	}
}
