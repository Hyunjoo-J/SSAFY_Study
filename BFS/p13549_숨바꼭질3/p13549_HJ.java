package BFS.p13549_숨바꼭질3;

import java.io.*;
import java.util.*;

public class p13549_HJ {
	public static class Hide {
		int now;
		int time;

		public Hide(int now, int time) {
			this.now = now;
			this.time = time;
		}
	}

	static int n, m, min = Integer.MAX_VALUE;
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		bfs();
		System.out.println(min);
	}

	public static void bfs() {
		Queue<Hide> q = new LinkedList<>();
		q.offer(new Hide(n, 0));

		while (!q.isEmpty()) {
			Hide tmp = q.poll();
			visited[tmp.now] = true;
			if (tmp.now == m)
				min = Math.min(min, tmp.time);
			if (tmp.now * 2 <= 100000 && visited[tmp.now * 2] == false) {
				q.offer(new Hide(tmp.now * 2, tmp.time));
			}
			if (tmp.now + 1 <= 100000 && visited[tmp.now + 1] == false) {
				q.offer(new Hide(tmp.now + 1, tmp.time + 1));
			}
			if (tmp.now - 1 >= 0 && visited[tmp.now - 1] == false) {
				q.offer(new Hide(tmp.now - 1, tmp.time + 1));
			}
		}
	}

}
