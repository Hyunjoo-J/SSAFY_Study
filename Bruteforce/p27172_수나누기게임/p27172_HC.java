package Bruteforce.p27172_수나누기게임;

import java.io.*;
import java.util.*;

public class p27172_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] x = new int[N];
		int[] counter = new int[1000001];
		boolean[] nums = new boolean[1000001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			x[i] = Integer.parseInt(st.nextToken());
			nums[x[i]] = true;
		}

		for (int i = 0; i < N; ++i) {
			int y = x[i] << 1;
			while (y < 1000001) {
				if (nums[y]) {
					--counter[y];
					++counter[x[i]];
				}
				y += x[i];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			sb.append(counter[x[i]]).append(" ");
		}
		System.out.println(sb.toString());
	    br.close();
	}
}
