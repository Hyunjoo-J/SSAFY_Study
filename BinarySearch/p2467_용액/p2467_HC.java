package BinarySearch.p2467_용액;

import java.io.*;
import java.util.*;

public class p2467_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int min = Integer.MAX_VALUE;
		int[] answer = new int[2];
		for (int i = 0; i < N; ++i) {
			int idx = lowerBound(arr, i + 1, N - 1, -arr[i]);

			if (idx - 1 > i) {
				int abs = Math.abs(arr[i] + arr[idx - 1]);
				if (abs < min) {
					min = abs;
					answer[0] = arr[i];
					answer[1] = arr[idx - 1];
				}
			}
			if (idx < N) {
				int abs = Math.abs(arr[i] + arr[idx]);
				if (abs < min) {
					min = abs;
					answer[0] = arr[i];
					answer[1] = arr[idx];
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	    br.close();
	}

	private static int lowerBound(int[] arr, int fromIndex, int toIndex, int k) {
		int left = fromIndex;
		int right = toIndex;
		int mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (arr[mid] >= k) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right + 1;
	}
}
