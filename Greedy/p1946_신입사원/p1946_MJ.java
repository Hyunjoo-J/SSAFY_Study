package Greedy.p1946_신입사원;

import java.util.*;
import java.io.*;

public class p1946_MJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int apply[] = new int[N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int paper = Integer.parseInt(st.nextToken());
				int talk = Integer.parseInt(st.nextToken());

				apply[paper] = talk;
			}

			int ans = 1;
			int standard = apply[1];
			for (int i = 2; i < N + 1; i++) {
				if (apply[i] < standard) {
					ans++;
					standard = apply[i];
				}
			}

			sb.append(ans).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
