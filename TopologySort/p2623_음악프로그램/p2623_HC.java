package TopologySort.p2623_음악프로그램;

import java.io.*;
import java.util.*;

public class p2623_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] inDegree = new int[N + 1];

		List<List<Integer>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			for (int j = 1; j < n; ++j) {
				int curr = Integer.parseInt(st.nextToken());
				graph.get(prev).add(curr);
				++inDegree[curr];
				prev = curr;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>(N + 1);
		for (int i = 1; i <= N; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}

		StringBuilder sb = new StringBuilder();
		boolean cycle = false;
		for (int i = 0; i < N; ++i) {
			if (queue.isEmpty()) {
				cycle = true;
				break;
			}
			int curr = queue.poll();
			sb.append(curr).append("\n");

			for (int next: graph.get(curr)) {
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		System.out.println(cycle ? 0 : sb.toString());
		br.close();
	}
}
