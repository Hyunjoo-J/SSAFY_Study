package BinarySearch.p2473_세용액;

import java.io.*;
import java.util.*;

public class p2473_HC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		long[] a = new long[N];


		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			a[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(a);

		long min = Long.MAX_VALUE;
		long sum;
		long[] res = new long[3];
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				sum = a[i] + a[j];
				int k = lowerBound(a, j + 1, N, -sum);

				if (k < N) {
					long abs = Math.abs(sum + a[k]);
					if (abs < min) {
						min = abs;
						res[0] = a[i];
						res[1] = a[j];
						res[2] = a[k];
					}
				}
				if (k > j + 1) {
					long abs = Math.abs(sum + a[k - 1]);
					if (abs < min) {
						min = abs;
						res[0] = a[i];
						res[1] = a[j];
						res[2] = a[k - 1];
					}
				}
			}
		}
		System.out.println(res[0] + " " + res[1] + " " + res[2]);
		br.close();
	}

	private static int lowerBound(long[] arr, int fromIndex, int toIndex, long key) {
		int left = fromIndex;
		int right = toIndex - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (arr[mid] >= key) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right + 1;
	}
}