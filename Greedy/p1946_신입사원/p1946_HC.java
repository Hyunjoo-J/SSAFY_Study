package Greedy.p1946_신입사원;

import java.io.*;
import java.util.*;

public class p1946_HC {

	private static class Pair {
		int first, second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N;
		Pair[] people = new Pair[100000];

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				people[i] = new Pair(Integer.parseInt(st.nextToken()),
									 Integer.parseInt(st.nextToken()));
			}

			Arrays.sort(people, 0, N, (o1, o2) -> {
				if (o1.first == o2.first)
					return Integer.compare(o2.second, o2.second);
				return Integer.compare(o1.first, o2.first);
			});

			int answer = N;
			int minS = people[0].second;
			for (int i = 1; i < N; ++i) {
				if (people[i].second > minS) {
					--answer;
				}
				minS = Math.min(minS, people[i].second);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}
}
