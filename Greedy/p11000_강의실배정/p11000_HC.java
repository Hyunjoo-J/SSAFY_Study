package Greedy.p11000_강의실배정;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Prefix sum
 */
public class p11000_HC {

	private static class Pair {
		int s, t;

		public Pair(int s, int t) {
			this.s = s;
			this.t = t;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Pair[] classes = new Pair[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			classes[i] = new Pair(Integer.parseInt(st.nextToken()),
								  Integer.parseInt(st.nextToken()));
		}

		int end = coordCompression(classes, N);
		int[] p = new int[end + 1];

		for (Pair cls: classes) {
			p[cls.s] += 1;
			p[cls.t] -= 1;
		}

		for (int i = 0; i < end; ++i) {
			p[i + 1] += p[i];
		}

		int answer = 0;
		for (int i = 0; i < end; ++i)  {
			answer = Math.max(answer, p[i]);
		}
		System.out.println(answer);
		br.close();
	}

	private static int coordCompression(Pair[] classes, int N) {
		Map<Integer, Integer> cMap = new HashMap<>();

		for (Pair cls: classes) {
			cMap.put(cls.s, 0);
			cMap.put(cls.t, 0);
		}
		List<Integer> sortedKeys = cMap.keySet()
			.stream()
			.sorted()
			.collect(Collectors.toList());

		int idx = 0;
		for (int key: sortedKeys) {
			cMap.put(key, idx++);
		}

		for (Pair cls: classes) {
			cls.s = cMap.get(cls.s);
			cls.t = cMap.get(cls.t);
		}
		return idx;
	}
}

