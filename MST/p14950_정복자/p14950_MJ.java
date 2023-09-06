package MST.p14950_정복자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p14950_MJ {

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
	static int N, M, t, parents[];
	static Edge arr[];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		t = Integer.parseInt(st.nextToken()); // 추가 비용
		arr = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[i] = new Edge(from, to, weight);
		}

		Arrays.sort(arr);

		make();

		int result = 0; // MST 비용
		int cnt = 0; // 연결된 간선의 개수
		int costCnt = 0; // 추가 비용 증가 횟수
		for (Edge e : arr) {
			if (union(e.from, e.to)) {
				result += e.weight + t * costCnt++;
				if (++cnt == N - 1)
					break;
			}
		}

		System.out.println(result);

		br.close();
	}

	static void make() {
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parents[rootB] = rootA;
		return true;
	}
}
