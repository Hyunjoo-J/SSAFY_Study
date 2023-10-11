package KMP.p11585_속타는저녁메뉴;

import java.io.*;
import java.util.*;

public class p11585_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char roul[], meat[];
	static int N, cnt;

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		meat = new char[N];
		roul = new char[N * 2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			meat[i] = st.nextToken().charAt(0);
		}

		int tmp = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			roul[i] = st.nextToken().charAt(0);
			if (meat[i] == roul[i])
				tmp++;
		}

		for (int i = N; i < 2 * N; i++) {
			roul[i] = roul[i - N];
		}

		int[] p = new int[N];

		for (int i = 1, j = 0; i < N; i++) {
			while (j > 0 && meat[i] != meat[j]) {
				j = p[j - 1];
			}
			if (meat[i] == meat[j])
				p[i] = ++j;
		}

//		System.out.println(Arrays.toString(p));

		for (int i = 0, j = 0; i < 2 * N; i++) {
			while (j > 0 && roul[i] != meat[j])
				j = p[j - 1];
			if (roul[i] == meat[j]) {
				if (j == N - 1) {
					cnt++;
					j = p[j];
				} else {
					j++;
				}
			}
		}

		if (tmp == N)
			cnt--;
		
		if (cnt == N)
			System.out.println("1/1");
		else {
			int a = gcd(N, cnt);
			System.out.println(cnt / a + "/" + N / a);
		}

	}

	static int gcd(int a, int b) {
		while (b > 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
