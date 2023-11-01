package UnionFind.p1043_거짓말;

import java.io.*;
import java.util.*;

public class p1043_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] parent = new int[N + 1];
		for (int i = 0; i <= N; ++i)
			parent[i] = i;

		st = new StringTokenizer(br.readLine());
		int numOfKnows = Integer.parseInt(st.nextToken());
		for (int i = 0, x; i < numOfKnows; ++i) {
			x = Integer.parseInt(st.nextToken());
			union(parent, x, 0);
		}

		int[][] parties = new int[M][];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int pp = Integer.parseInt(st.nextToken());

			parties[i] = new int[pp];
			for (int j = 0; j < pp; ++j) {
				parties[i][j] = Integer.parseInt(st.nextToken());
				union(parent, parties[i][0], parties[i][j]);
			}
		}

		int answer = 0;
		for (int i = 0; i < M; ++i) {
			boolean flag = false;
			for (int j = 0, pp = parties[i].length; j < pp; ++j) {
				if (find(parent, parties[i][j]) == find(parent, 0)) {
					flag = true;
				}
			}
			if (!flag)
				++answer;
		}
		System.out.println(answer);
	    br.close();
	}

	private static int find(int[] parent, int x) {
		if (parent[x] != x)
			parent[x] = find(parent, parent[x]);
		return parent[x];
	}

	private static void union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}
}
