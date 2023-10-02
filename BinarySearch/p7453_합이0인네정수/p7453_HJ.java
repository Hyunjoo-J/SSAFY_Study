package BinarySearch.p7453_합이0인네정수;

import java.util.*;
import java.io.*;
public class p7453_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		int[] CD = new int[N*N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				CD[idx++] = C[i] + D[j];
			}
		}

		Arrays.sort(CD);



		long ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = A[i] + B[j];
				int upper = upperBound(-temp, CD);
				int lower = lowerBound(-temp, CD);
				ans += (upper - lower);
			}
		}
		System.out.println(ans);
	}
	private static int upperBound(int key, int[] arr) {
		int start = 0, end = arr.length-1;
		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] > key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}

	private static int lowerBound(int key, int[] arr) {
		int start = 0, end = arr.length-1;
		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] >= key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
}
