package BFS_DFS.p16236_아기상어;

import java.io.*;
import java.util.*;

public class p16236_MJ {

	static class Fish {
		int x;
		int y;
		int size;

		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], cnt, eat, ans = 0;
	static boolean visited[][];
	static Fish shark, target;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 9) {
					shark = new Fish(i, j, 2);
					map[i][j] = 0;
				}
			}
		}

		// 먹을 수 있는 물고기가 있을 때
		while (eat(shark) != null) {
			// 먹은 물고기의 개수가 상어의 size와 동일할 때
			if(eat==shark.size) {
				// 상어의 size를 1 늘려주고, 먹은 물고기의 개수를 0으로 초기화 시켜준다.
				shark.size += 1;
				eat = 0;
			}
			// 이동 횟수를 더해준다.
			ans += cnt;
		}

		System.out.println(ans);
		
		br.close();
	}

	private static Fish eat(Fish shark) {
		Queue<Fish> queue = new LinkedList<Fish>();
		queue.offer(shark);
		cnt = 0;
		target = null;
		visited = new boolean[N][N];
		visited[shark.x][shark.y] = true;

		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			cnt++;
			for (int qs = 0; qs < queueSize; qs++) {
				Fish cur = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nowX = cur.x + dx[i];
					int nowY = cur.y + dy[i];

					if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= N)
						continue;

					if (visited[nowX][nowY])
						continue;

					if (map[nowX][nowY] > cur.size)
						continue;

					if (0 < map[nowX][nowY] && map[nowX][nowY] < cur.size) {
						if (target == null)
							target = new Fish(nowX, nowY, map[nowX][nowY]);
						else
							target = eatFirst(target, new Fish(nowX, nowY, map[nowX][nowY]));
					}

					if (map[nowX][nowY] == cur.size || map[nowX][nowY] == 0) {
						queue.offer(new Fish(nowX, nowY, cur.size));
						visited[nowX][nowY] = true;
					}
				}
			}
			
			// 먹을 수 있는 물고기를 찾았으면
			if (target != null) {
				shark.x = target.x;
				shark.y = target.y;
				eat++;
				map[target.x][target.y] = 0;
				return target;
			}
		}
		
		return null;
	}

	// 거리가 같은 경우, 1) 가장 위에 있을 때 2) 가장 왼쪽에 있을 때 순으로 먹는다.
	private static Fish eatFirst(Fish target, Fish nowFish) {
		if (target.x < nowFish.x) {
			return target;
		} else if (target.x > nowFish.x) {
			return nowFish;
		} else {
			if (target.y < nowFish.y) {
				return target;
			} else {
				return nowFish;
			}
		}
	}
}
