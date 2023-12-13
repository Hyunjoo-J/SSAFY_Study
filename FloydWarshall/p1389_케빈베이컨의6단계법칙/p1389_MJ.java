package FloydWarshall.p1389_케빈베이컨의6단계법칙;

import java.util.*;
import java.io.*;

public class p1389_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static ArrayList<Integer> arr[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a].add(b);
			arr[b].add(a);
		}

		int minSum = Integer.MAX_VALUE;
		int minUser = 0;
		for (int i = 1; i < N + 1; i++) {
			if (minSum > bfs(i)) {
				minSum = bfs(i);
				minUser = i;
			}
		}

		System.out.println(minUser);

		br.close();
	}

	private static int bfs(int n) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		boolean visited[] = new boolean[N + 1];
		int round = 0;
		int sum = 0;

		top: while (!queue.isEmpty()) {
			int queueSize = queue.size();

			for (int qs = 0; qs < queueSize; qs++) {
				int now = queue.poll();
				if (!visited[now]) {
					visited[now] = true;
					sum += round;

					for (int i = 0; i < arr[now].size(); i++) {
						int next = arr[now].get(i);
						queue.add(next);
					}
				}
			}

			round++;

			for (int i = 1; i < N + 1; i++) {
				if (!visited[i])
					continue top;
			}
			break;
		}

		return sum;
	}
}
