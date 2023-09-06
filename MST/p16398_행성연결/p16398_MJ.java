package MST.p16398_행성연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p16398_MJ {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, edgeMap[][], parents[];
	static Edge edgeArr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		edgeMap = new int[N + 1][N + 1];
		edgeArr = new Edge[N * (N - 1) / 2];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				edgeMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int tmp = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				edgeArr[tmp++] = new Edge(i, j, edgeMap[i][j]);
			}
		}

		Arrays.sort(edgeArr);

		make();

		long ans = 0;
		int cnt = 0;
		for (Edge e : edgeArr) {
			if (union(e.from, e.to)) {
				ans += e.weight;
				if (++cnt == N - 1)
					break;
			}
		}

		System.out.println(ans);

		br.close();
	}

	private static void make() {
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parents[rootB] = rootA;
		return true;
	}
}
