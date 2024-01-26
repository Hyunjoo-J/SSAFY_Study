package BinarySearch.p10816_숫자카드2;

import java.io.*;
import java.util.*;

public class p10816_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		mergeSort(A, 0, N - 1);

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(upperBound(A, num) - lowerBound(A, num)).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int upperBound(int[] A, int num) {
		int left = 0;
		int right = A.length - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (A[mid] > num) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int lowerBound(int[] A, int num) {
		int left = 0;
		int right = A.length - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (A[mid] >= num) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right + 1;
	}

	private static void merge(int[] A, int[] sorted, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int idx = left;

		while (i <= mid && j <= right) {
			if (A[i] < A[j])
				sorted[idx++] = A[i++];
			else
				sorted[idx++] = A[j++];
		}

		while (i <= mid)
			sorted[idx++] = A[i++];

		while (j <= right)
			sorted[idx++] = A[j++];

		for (i = left; i <= right; ++i)
			A[i] = sorted[i];
	}

	private static void mergeSort(int[] A, int from, int to) {
		int[] sorted = new int[to - from + 1];
		int left, mid, right, size;
		for (size = 1; size <= to; size <<= 1) {
			for (left = from; left <= to - size; left += (size << 1)) {
				mid = left + size - 1;
				right = Math.min(to, left + (size << 1) - 1);
				merge(A, sorted, left, mid, right);
			}
		}
	}
}