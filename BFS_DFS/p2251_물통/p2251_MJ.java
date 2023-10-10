package BFS_DFS.p2251_물통;

import java.util.*;
import java.io.*;

public class p2251_MJ {

	static class Water {
		int waterA;
		int waterB;
		int waterC;

		public Water(int waterA, int waterB, int waterC) {
			this.waterA = waterA;
			this.waterB = waterB;
			this.waterC = waterC;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int bottleA, bottleB, bottleC, tmpAns[], ans[], cnt;
	static boolean visited[][][];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		bottleA = Integer.parseInt(st.nextToken());
		bottleB = Integer.parseInt(st.nextToken());
		bottleC = Integer.parseInt(st.nextToken());
		visited = new boolean[bottleA + 1][bottleB + 1][bottleC + 1];
		tmpAns = new int[200];

		bfs();

		ans = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			ans[i] = tmpAns[i];
		}

		Arrays.sort(ans);

		for (int i = 0; i < cnt; i++) {
			System.out.print(ans[i] + " ");
		}

		br.close();
	}

	public static void bfs() {
		Queue<Water> queue = new LinkedList<Water>();
		queue.offer(new Water(0, 0, bottleC));

		while (!queue.isEmpty()) {
			Water cur = queue.poll();
			int curA = cur.waterA;
			int curB = cur.waterB;
			int curC = cur.waterC;

			if (visited[curA][curB][curC])
				continue;
			if (curA == 0) {
				tmpAns[cnt++] = curC;
			}
			visited[curA][curB][curC] = true;

			// A -> B
			if (curA + curB > bottleB)
				queue.offer(new Water(curA - (bottleB - curB), bottleB, curC));
			else
				queue.offer(new Water(0, curA + curB, curC));

			// A -> C
			if (curA + curC > bottleC)
				queue.offer(new Water(curA - (bottleC - curC), curB, bottleC));
			else
				queue.offer(new Water(0, curB, curA + curC));

			// B -> A
			if (curA + curB > bottleA)
				queue.offer(new Water(bottleA, curB - (bottleA - curA), curC));
			else
				queue.offer(new Water(curA + curB, 0, curC));

			// B -> C
			if (curB + curC > bottleC)
				queue.offer(new Water(curA, curB - (bottleC - curC), bottleC));
			else
				queue.offer(new Water(curA, 0, curB + curC));

			// C -> A
			if (curA + curC > bottleA)
				queue.offer(new Water(bottleA, curB, curC - (bottleA - curA)));
			else
				queue.offer(new Water(curA + curC, curB, 0));

			// c -> B
			if (curB + curC > bottleB)
				queue.offer(new Water(curA, bottleB, curC - (bottleB - curB)));
			else
				queue.offer(new Water(curA, curB + curC, 0));
		}
	}
}
