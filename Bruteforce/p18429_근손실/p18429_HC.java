package Bruteforce.p18429_근손실;

import java.io.*;
import java.util.*;

public class p18429_HC {

	private static int N, K;
	private static int[] A;
	private static int[] perm;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		perm = new int[N];
		for (int i = 0; i < N; ++i) {
			perm[i] = i;
		}

		int answer = 0;
		do {
			if (simul())
				++answer;
		} while (np(perm, N));

		System.out.println(answer);
	    br.close();
	}

	private static boolean simul() {
		int power = 500;
		for (int i = 0; i < N; ++i) {
			power += (A[perm[i]] - K);
			if (power < 500)
				return false;
		}
		return true;
	}

	private static boolean np(int[] perm, int n) {
		int i = n - 1;
		while (i > 0 && perm[i - 1] > perm[i])
			--i;

		if (i == 0)
			return false;

		int j = n - 1;
		while (perm[i - 1] > perm[j])
			--j;

		swap(perm, i - 1, j);

		int k = n - 1;
		while (i < k)
			swap(perm, i++, k--);

		return true;
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
