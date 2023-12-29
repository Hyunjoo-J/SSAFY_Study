package TopologySort.p14567_선수과목;

import java.io.*;
import java.util.*;

public class p14567_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] inDegree = new int[N + 1];
		List<List<Integer>> graph = new ArrayList<>(N + 1);

		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0, A, B; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			++inDegree[B];
		}

		// topology sort
		int[] answer = new int[N + 1];

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; ++i) {
			if (inDegree[i] == 0) {
				queue.add(i);
				answer[i] = 1;
			}
		}

		for (int i = 0; i < N; ++i) {
			int now = queue.remove();

			for (int next: graph.get(now)) {
				answer[next] = Math.max(answer[next], answer[now] + 1);
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		// print
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
	    br.close();
	}
}
