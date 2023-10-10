package BinarySearch.p7453_합이0인네정수;

import java.io.*;
import java.util.*;

public class p7453_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int A[], B[], C[], D[], AB[], CD[], cnt = 0; 
	static long ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		C = new int[N];
		D = new int[N];
		AB = new int[N * N];
		CD = new int[N * N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[cnt] = A[i] + B[j];
				CD[cnt] = C[i] + D[j];
				cnt++;
			}
		}

		Arrays.sort(CD);

		for (int i = 0; i < N * N; i++) {
			int num = AB[i];
			
			// 이분 탐색의 중복값을 뽑아내기 위해서 사용
			int upper = upperBound(-num, CD);
			int lower = lowerBound(-num, CD);
			ans += (upper - lower);
		}

		System.out.println(ans);

		br.close();
	}

	private static int upperBound(int key, int[] arr) {
		int low = 0;
		int high = N * N - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (CD[mid] > key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return high;
	}

	private static int lowerBound(int key, int[] arr) {
		int low = 0;
		int high = N * N - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (CD[mid] >= key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return high;
	}
}
