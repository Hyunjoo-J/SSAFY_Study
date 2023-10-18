package BinarySearch.p2512_예산;

import java.io.*;
import java.util.*;

public class p2512_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, city[], left, max = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		city = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		left = M;

		Arrays.sort(city);

		for (int i = 0; i < N; i++) {
			if (left >= city[i] * (N - i)) {
				left -= city[i];
				max = city[i];
			} else {
				max = left / (N - i);
				break;
			}
		}

		System.out.println(max);
	
		br.close();
	}
}
