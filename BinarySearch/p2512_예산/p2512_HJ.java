package BinarySearch.p2512_예산;
import java.util.*;
import java.io.*;

public class p2512_HJ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int left = 0;
		int right = 0;
		for (int i = 0; i < N; ++i) {
			budget[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, budget[i]);
		}
		int M = Integer.parseInt(br.readLine());
		while (left <= right) {
			int mid = (left + right) >> 1;
			long ans = 0;
			for (int i = 0; i < N; ++i) {
				if (budget[i] > mid)
					ans += mid;
				else
					ans += budget[i];
			}
			if (ans <= M) {
				left = mid + 1;
			} else
				right = mid - 1;
		}
		System.out.println(right);
	}

}