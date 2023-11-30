package Greedy.p2138_전구와스위치;

import java.io.*;
import java.util.*;

public class p2138_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] start, nowA, nowB, ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		start = new int[N];
		ans = new int[N];

		String tmp = br.readLine();
		for (int i = 0; i < N; i++)
			start[i] = tmp.charAt(i) - '0';

		tmp = br.readLine();
		for (int i = 0; i < N; i++)
			ans[i] = tmp.charAt(i) - '0';

		nowA = new int[N]; // 첫 번째 스위치 켠 상태
		nowB = new int[N]; // 첫 번째 스위치 끈 상태

		int cntA = 1;
		int cntB = 0;

		nowA = start.clone();
		nowB = start.clone();

		nowA[0] = (nowA[0] == 0) ? 1 : 0;
		nowA[1] = (nowA[1] == 0) ? 1 : 0;

		for (int i = 1; i < N; i++) {
			if (nowA[i - 1] != ans[i - 1]) {
				switchOn(i, nowA);
				cntA++;
			}
			if (nowB[i - 1] != ans[i - 1]) {
				switchOn(i, nowB);
				cntB++;
			}
		}

		if (Arrays.equals(nowA, ans)) {
			if (Arrays.equals(nowB, ans)) {
				System.out.println(Math.min(cntA, cntB));
			} else {
				System.out.println(cntA);
			}
		} else if (Arrays.equals(nowB, ans)) {
			System.out.println(cntB);
		} else {
			System.out.println(-1);
		}

		br.close();
	}

	private static void switchOn(int i, int[] switchArr) {
		switchArr[i - 1] = (switchArr[i - 1] == 0) ? 1 : 0;
		switchArr[i] = (switchArr[i] == 0) ? 1 : 0;
		if (i < N - 1) {
			switchArr[i + 1] = (switchArr[i + 1] == 0) ? 1 : 0;
		}
	}
}
