package Greedy.p11501_주식;

import java.util.*;
import java.io.*;

public class p11501_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int num[] = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			long benefit = 0;
			int buy = 0;
			int buyNum = 0;
			int sell = num[N - 1];
			for (int i = N - 2; i >= 0; i--) {
				if (sell > num[i]) {
					buy += num[i];
					buyNum++;
					if (i == 0) {
						benefit += (buyNum * sell - buy);
					}
				} else {
					benefit += (buyNum * sell - buy);
					buy = 0;
					buyNum = 0;
					sell = num[i];
				}
			}

			sb.append(benefit).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
