package BinarySearch.p2110_공유기설치;

import java.io.*;
import java.util.*;

public class p2110_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, C, low, mid, high, start, cnt, ans = 0;
	static int house[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		house = new int[N];
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(house);

		low = 1;
		high = house[N - 1] - house[0];
		mid = (low + high) / 2;

		while (low <= high) {
			cnt = 1;
			start = house[0];

			for (int i = 0; i < N; i++) {
				if (house[i] - start >= mid) {
					start = house[i];
					cnt++;
				}
			}

			if (cnt < C) {
				high = mid - 1;
			} else {
				ans = Math.max(mid, ans);
				low = mid + 1;
			}

			mid = (low + high) / 2;
		}
		
		System.out.println(ans);
		
		br.close();
	}
}
