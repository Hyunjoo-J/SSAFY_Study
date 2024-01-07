package Bitmasking.p15787_기차가어둠을헤치고은하수를;

import java.io.*;
import java.util.*;

public class p15787_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] train = new int[N + 1];
		int fullVisit = (1 << 20) - 1;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			if (c == 1) {
				int x = Integer.parseInt(st.nextToken()) - 1;
				train[i] |= (1 << x);
			} else if (c == 2) {
				int x = Integer.parseInt(st.nextToken()) - 1;
				train[i] &= ~(1 << x);
			} else if (c == 3) {
				train[i] <<= 1;
				train[i] &= fullVisit;
			} else if (c == 4) {
				train[i] >>= 1;
				train[i] &= fullVisit;
			}
		}

		Set<Integer> set = new HashSet<>();
		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			if (!set.contains(train[i])) {
				set.add(train[i]);
				++answer;
			}
		}
		System.out.println(answer);
	    br.close();
	}
}
