package BFS_DFS.p13549_숨바꼭질3;

import java.util.*;
import java.io.*;

public class p13549_MJ {

	static class Node {
		int x;
		int time;

		public Node(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, min = 100001;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];

		bfs();
		System.out.println(min);

		br.close();
	}

	public static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(N, 0));

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			visited[curNode.x] = true;

			if (curNode.x == K) {
				min = Math.min(curNode.time, min);
			}

			if (curNode.x * 2 <= 100000 && !visited[curNode.x * 2])
				queue.offer(new Node(curNode.x * 2, curNode.time));
			if (curNode.x + 1 <= 100000 && !visited[curNode.x + 1])
				queue.offer(new Node(curNode.x + 1, curNode.time + 1));
			if (curNode.x - 1 >= 0 && !visited[curNode.x - 1])
				queue.offer(new Node(curNode.x - 1, curNode.time + 1));
		}
	}
}
