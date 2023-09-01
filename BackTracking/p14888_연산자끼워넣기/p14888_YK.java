package p14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14888_YK {
	static int r = 2000;
	static int n;
	static int[][] arr;

	public static void f(int t1, int t2, int[] ta, int[] tb) {
		if (t1 == n / 2 && t2 == n / 2) {
			int a = cal(ta);
			int b = cal(tb);
			int k = a > b ? a - b : b - a;
			if (k < r) r = k;
			return;
		}

		if (t1 != n / 2) {
			ta[t1] = t1 + t2;
			f(++t1, t2, ta, tb);
			t1--;
		}
		if (t2 != n / 2) {
			tb[t2] = t1 + t2;
			f(t1, ++t2, ta, tb);
			t2--;
		}

	}

	public static int cal(int[] team) {
		int sum = 0;
		for (int i = 0; i < team.length - 1; i++) {
			for (int j = i + 1; j < team.length; j++) {
				sum += arr[team[i]][team[j]] + arr[team[j]][team[i]];
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		f(0, 0, new int[n/2], new int[n/2]);
		System.out.println(r);
	}
}