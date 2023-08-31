package TopologySort.p5021_왕위계승;

import java.io.*;
import java.util.*;

public class p5021_HC {

	private static final int MAX_PEOPLE = 200;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Map<String, Integer> map = new HashMap<>();
		double[] blood = new double[MAX_PEOPLE];
		int[] inDegree = new int[MAX_PEOPLE];
		int pIdx = 0;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String king = br.readLine();
		blood[pIdx] = 1.0;
		map.put(king, pIdx++);

		List<List<Integer>> graph = new ArrayList<>(MAX_PEOPLE);
		for (int i = 0; i < MAX_PEOPLE; ++i)
			graph.add(new ArrayList<>());

		String child, parent1, parent2;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			child = st.nextToken();
			parent1 = st.nextToken();
			parent2 = st.nextToken();
			if (!map.containsKey(child))
				map.put(child, pIdx++);
			if (!map.containsKey(parent1))
				map.put(parent1, pIdx++);
			if (!map.containsKey(parent2))
				map.put(parent2, pIdx++);
			graph.get(map.get(parent1)).add(map.get(child));
			graph.get(map.get(parent2)).add(map.get(child));
			inDegree[map.get(child)] += 2;
		}

		Queue<Integer> queue = new ArrayDeque<>(MAX_PEOPLE);
		for (int i = 0; i < MAX_PEOPLE; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int next: graph.get(curr)) {
				blood[next] += blood[curr] / 2;
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		double max = 0;
		String name, answer = null;
		for (int i = 0; i < M; ++i) {
			name = br.readLine();
			if (!map.containsKey(name))
				continue;
			int v = map.get(name);
			if (max < blood[v]) {
				max = blood[v];
				answer = name;
			}
		}
		System.out.println(answer);
		br.close();
	}
}
