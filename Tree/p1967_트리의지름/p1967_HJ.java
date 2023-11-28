package Tree.p1967_트리의지름;

import java.io.*;
import java.util.*;

public class p1967_HJ {
	static ArrayList<Node>[] list;
	static int n;
	static int max = 0;
	static boolean[] visited;
	static int max_idx = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			String[] t = br.readLine().split(" ");
			int parent = Integer.parseInt(t[0]);
			int child = Integer.parseInt(t[1]);
			int weight = Integer.parseInt(t[2]);
			list[parent].add(new Node(child, weight));
			list[child].add(new Node(parent, weight));
		}

		visited = new boolean[n + 1];
		visited[1] = true;
		dfs(1, 0);


		visited = new boolean[n + 1];
		visited[max_idx] = true;
		dfs(max_idx, 0);
		System.out.println(max);

	}

	public static void dfs(int idx, int cnt) {

		if (max < cnt) {
			max = cnt;
			max_idx = idx;
		}
		for (Node a : list[idx]) {
			if (!visited[a.idx]) {
				visited[a.idx] = true;
				dfs(a.idx, cnt + a.cnt);
			}
		}
	}
}

class Node {
	int idx, cnt;

	Node(int idx, int cnt) {
		this.idx = idx;
		this.cnt = cnt;
	}
}
