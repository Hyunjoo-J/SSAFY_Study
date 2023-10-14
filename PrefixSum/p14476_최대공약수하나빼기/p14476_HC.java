package PrefixSum.p14476_최대공약수하나빼기;

import java.io.*;
import java.util.*;

public class p14476_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] gcdLtoR = new int[N];
		int[] gcdRtoL = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		gcdLtoR[0] = nums[0];
		for (int i = 1; i < N; ++i) {
			gcdLtoR[i] = gcd(nums[i], gcdLtoR[i - 1]);
		}
		gcdRtoL[N - 1] = nums[N - 1];
		for (int i = N - 2; i > -1; --i) {
			gcdRtoL[i] = gcd(nums[i], gcdRtoL[i + 1]);
		}

		int maxGcd = -1;
		int k = 0;
		if (nums[0] % gcdRtoL[1] != 0 && maxGcd < gcdRtoL[1]) {
			maxGcd = gcdRtoL[1];
			k = nums[0];
		}
		if (nums[N - 1] % gcdLtoR[N - 2] != 0 && maxGcd < gcdLtoR[N - 2]) {
			maxGcd = gcdLtoR[N - 2];
			k = nums[N - 1];
		}
		for (int i = 1; i < N - 1; ++i) {
			int gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
			if (nums[i] % gcd != 0 && maxGcd < gcd) {
				maxGcd = gcd;
				k = nums[i];
			}
		}

		System.out.println(maxGcd + " " + (maxGcd != -1 ? k : ""));
		br.close();
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
}

