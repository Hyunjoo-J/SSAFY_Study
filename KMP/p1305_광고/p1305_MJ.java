package KMP.p1305_광고;

import java.io.*;
import java.util.*;

public class p1305_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char adv[];
	static int N, ans;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		adv = new char[N];

		String tmp = br.readLine();
		for (int i = 0; i < N; i++) {
			adv[i] = tmp.charAt(i);
		}

		int[] p = new int[N];

		for (int i = 1, j = 0; i < N; i++) {
			while (j > 0 && adv[i] != adv[j]) {
				j = p[j - 1];
			}
			if (adv[i] == adv[j])
				p[i] = ++j;
		}

//		System.out.println(Arrays.toString(p));

		ans = N - p[N-1];
		System.out.println(ans);
	}
}
