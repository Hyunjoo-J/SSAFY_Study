package BinarySearch.p7453_합이0인네정수;

import java.io.*;
import java.util.*;

public class p7453_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		int n2 = n * n;
		int[] pair = new int[n2];
		int[] sortedPair = new int[n2];

		for (int i = 0, idx = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				pair[idx] = A[i] + B[j];
				sortedPair[idx++] = C[i] + D[j];
			}
		}
		Arrays.sort(sortedPair);

		long answer = 0;
		for (int num: pair) {
			answer += upperBound(sortedPair, -num, 0, n2 - 1) -
						lowerBound(sortedPair, -num, 0, n2 - 1);
		}
		System.out.println(answer);
	    br.close();
	}

	private static int lowerBound(int[] arr, int value, int left, int right) {
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (arr[mid] >= value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	private static int upperBound(int[] arr, int value, int left, int right) {
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (arr[mid] <= value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left - 1;
	}
}
