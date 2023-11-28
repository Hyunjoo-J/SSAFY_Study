package BFS_DFS.p2310_어드벤처게임;

import java.io.*;
import java.util.*;

public class p2310_HC {

	private static final char ROOM_EMPTY = 'E';
	private static final char ROOM_LEPRECHAUN = 'L';
	private static final char ROOM_TROLL = 'T';

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;

		char[] type = new char[1001];
		int[] price = new int[1001];
		List<List<Integer>> graph = new ArrayList<>(1001);
		for (int i = 0; i <= 1000; ++i)
			graph.add(new ArrayList<>());

		int n;
		while ((n = Integer.parseInt(br.readLine())) > 0) {
			for (int i = 1; i <= n; ++i)
				graph.get(i).clear();

			for (int i = 1, next; i <= n; ++i) {
				st = new StringTokenizer(br.readLine());
				type[i] = st.nextToken().charAt(0);
				price[i] = Integer.parseInt(st.nextToken());
				while ((next = Integer.parseInt(st.nextToken())) > 0) {
					graph.get(i).add(next);
				}
			}
			if (isReachable(graph, n, type, price)) {
				bw.write("Yes\n");
			} else {
				bw.write("No\n");
			}
		}
		bw.flush();
	    bw.close();
	    br.close();
	}

	private static boolean isReachable(List<List<Integer>> graph, int n, char[] type, int[] price) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(1, 0));
		boolean[][] visited = new boolean[n + 1][501];
		visited[1][0] = true;
		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (now.idx == n) {
				return true;
			}

			int nextPrice = 0;
			for (int next: graph.get(now.idx)) {
				switch (type[next]) {
					case ROOM_EMPTY:
						nextPrice = now.price; break;
					case ROOM_LEPRECHAUN:
						nextPrice = Math.max(now.price, price[next]); break;
					case ROOM_TROLL:
						nextPrice = now.price - price[next]; break;
				}
				if (nextPrice >= 0 && !visited[next][nextPrice]) {
					visited[next][nextPrice] = true;
					queue.add(new Node(next, nextPrice));
				}
			}
		}
		return false;
	}

	private static class Node {
		int idx, price;

		public Node(int idx, int price) {
			this.idx = idx;
			this.price = price;
		}
	}
}
