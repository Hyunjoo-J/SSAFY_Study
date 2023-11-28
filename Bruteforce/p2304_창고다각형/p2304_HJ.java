package Bruteforce.p2304_창고다각형;

import java.util.*;
import java.io.*;

public class p2304_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] stick = new int[N][2];
		int max = 0;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			stick[i][0] = Integer.parseInt(st.nextToken());
			stick[i][1] = Integer.parseInt(st.nextToken());
			max = Math.max(max, stick[i][1]);
		}

		Arrays.sort(stick, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		int idx = 0;
		for (int i = 0; i < N; ++i)
			if (stick[i][1] == max)
				idx = i;

		int ans = max;
		int x = stick[0][0];
		int h = stick[0][1];
		for (int i = 0; i <= idx; ++i) {
			if (stick[i][1] >= h) {
				ans += (stick[i][0] - x) * h;
				x = stick[i][0];
				h = stick[i][1];
			}
		}
		x = stick[N - 1][0];
		h = stick[N - 1][1];
		for (int i = N - 1; i >= idx; --i) {
			if(stick[i][1] >= h){
				ans += (x - stick[i][0]) * h;
				x = stick[i][0];
				h = stick[i][1];
			}
		}
		System.out.println(ans);
	}
}