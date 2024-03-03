package Dijkstra.p1486_등산;

import java.io.*;
import java.util.*;

public class p1486_HC {

	private static final int INF = 123456789;
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				if (line.charAt(j) < 'a')
					graph[i][j] = line.charAt(j) - 'A';
				else
					graph[i][j] = line.charAt(j) - 'a' + 26;
			}
		}

		// dijkstra
		int[][] upFromHotel = dijkstra(graph, N, M, T, diff -> (diff < 0 ? square(diff) : 1));
		int[][] downToHotel = dijkstra(graph, N, M, T, diff -> (diff > 0 ? square(diff) : 1));

		int answer = -1;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (upFromHotel[i][j] + downToHotel[i][j] > D)
					continue;
				answer = Math.max(answer, graph[i][j]);
			}
		}
		System.out.println(answer);
	    br.close();
	}
	
	private static int[][] dijkstra(int[][] graph, int n, int m, int bound, Operation operation) {
		Queue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; ++i) {
			Arrays.fill(distance[i], INF);
		}
		heap.add(new Node(0, 0, 0));
		distance[0][0] = 0;
		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.x][currNode.y] < currNode.cost)
				continue;

			int nx, ny, cost, diff;
			for (int i = 0; i < 4; ++i) {
				nx = currNode.x + dx[i];
				ny = currNode.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;
				diff = graph[currNode.x][currNode.y] - graph[nx][ny];
				if (abs(diff) > bound)
					continue;
				cost = currNode.cost + operation.costOperation(diff);
				if (distance[nx][ny] > cost) {
					heap.add(new Node(nx, ny, cost));
					distance[nx][ny] = cost;
				}
			}
		}
		return distance;
	}

	private static int abs(int x) {
		return x > 0 ? x : -x;
	}

	private static int square(int x) {
		return x * x;
	}

	private interface Operation {
		int costOperation(int diff);
	}

	private static class Node {
		int x, y, cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}
