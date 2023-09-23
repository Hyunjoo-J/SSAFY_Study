package DataStructure.p1202_보석도둑;

import java.io.*;
import java.util.*;

public class p1202_HC {

	private static class Pair {
		int m, v;

		public Pair(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Pair[] jewel = new Pair[N];
		int[] weight = new int[K];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			jewel[i] = new Pair(
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken())
			);
		}
		for (int i = 0; i < K; ++i) {
			weight[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(weight);
		Arrays.sort(jewel, Comparator.comparingInt(o -> o.m));
		PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

		int jIdx = 0;
		long answer = 0;
		for (int c: weight) {
			while (jIdx < N && jewel[jIdx].m <= c) {
				heap.add(jewel[jIdx].v);
				++jIdx;
			}

			if (!heap.isEmpty())
				answer += heap.remove();
		}
		System.out.println(answer);
	    br.close();
	}
}
