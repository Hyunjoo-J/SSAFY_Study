package BFS_DFS.p30396_기사들의여행;

import java.io.*;
import java.util.*;

public class p30396_HC {

	private static final int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
	private static final int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int[][] original = new int[4][4];
		int[][] target = new int[4][4];

		for (int i = 0; i < 4; ++i) {
			String line = br.readLine();
			for (int j = 0; j < 4; ++j) {
				original[i][j] = line.charAt(j) - '0';
			}
		}
		for (int i = 0; i < 4; ++i) {
			String line = br.readLine();
			for (int j = 0; j < 4; ++j) {
				target[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(bfs(original, target));
	    br.close();
	}

	private static int bfs(int[][] original, int[][] target) {
		int bitmask = convertGraphToBitmask(original);
		int targetBitmask = convertGraphToBitmask(target);
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[1 << 16];

		queue.add(bitmask);
		visited[bitmask] = true;
		int move = -1;
		while (!queue.isEmpty()) {
			++move;
			int queueSize = queue.size();
			for (int q = 0; q < queueSize; ++q) {
				assert !queue.isEmpty();
				int now = queue.poll();

				if (now == targetBitmask) {
					queue.clear();
					break;
				}

				// find next graph state
				int[][] graph = convertBitmaskToGraph(now);
				for (int x = 0; x < 4; ++x) {
					for (int y = 0; y < 4; ++y) {
						if (graph[x][y] == 0)
							continue;
						for (int i = 0; i < 8; ++i) {
							int nx = x + dx[i];
							int ny = y + dy[i];
							if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
								continue;
							if (graph[nx][ny] == 1)
								continue;
							swap(graph, x, y, nx, ny);
							int nextBitmask = convertGraphToBitmask(graph);
							if (!visited[nextBitmask]) {
								visited[nextBitmask] = true;
								queue.add(nextBitmask);
							}
							swap(graph, x, y, nx, ny);
						}
					}
				}
			}
		}
		return move;
	}

	private static void swap(int[][] graph, int x1, int y1, int x2, int y2) {
		int temp = graph[x1][y1];
		graph[x1][y1] = graph[x2][y2];
		graph[x2][y2] = temp;
	}

	private static int convertGraphToBitmask(int[][] graph) {
		int bitmask = 0;
		for (int i = 0, index = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j, ++index) {
				if (graph[i][j] == 1) {
					bitmask |= (1 << index);
				}
			}
		}
		return bitmask;
	}

	private static int[][] convertBitmaskToGraph(int bitmask) {
		int[][] graph = new int[4][4];
		for (int i = 0, index = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j, ++index) {
				if ((bitmask & (1 << index)) > 0)
					graph[i][j] = 1;
			}
		}
		return graph;
	}
}
