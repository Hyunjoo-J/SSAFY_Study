package TopologySort.p2056_작업;

import java.io.*;
import java.util.*;

public class p2056_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] inDegree = new int[N + 1];
		int[] time = new int[N + 1];
		List<List<Integer>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 1, x; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			st.nextToken();
			while (st.hasMoreTokens()) {
				x = Integer.parseInt(st.nextToken());
				graph.get(x).add(i);
				++inDegree[i];
			}
		}

		int answer = 0;
		int[] complete = new int[N + 1];

		Queue<Integer> queue = new ArrayDeque<>(N + 1);
		for (int i = 1; i <= N; ++i) {
			if (inDegree[i] == 0) {
				queue.add(i);
				complete[i] = time[i];
				answer = Math.max(answer, complete[i]);
			}
		}

		for (int i = 0; i < N; ++i) {
			int curr = queue.poll();

			for (int next: graph.get(curr)) {
				complete[next] = Math.max(complete[next], complete[curr] + time[next]);
				answer = Math.max(answer, complete[next]);
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		System.out.println(answer);
		br.close();
	}
}
