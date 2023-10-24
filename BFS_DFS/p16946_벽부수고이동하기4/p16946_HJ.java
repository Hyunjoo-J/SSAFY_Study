package BFS_DFS.p16946_벽부수고이동하기4;
import java.util.*;
import java.io.*;

public class p16946_HJ {
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		List<Integer> list = new ArrayList<>();
		list.add(-1);
		int num = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (visited[i][j] == 0 && map[i][j] == 0) {
					list.add(bfs(i, j, ++num));
				}
			}
		}
		int[][] ans = new int[N][M];
		int[] move = new int[num + 1];
		Arrays.fill(move, -1);
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 0)
					continue;
				int res = 0;
				for (int d = 0; d < 4; ++d) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (!isRange(nx, ny))
						continue;
					if (map[nx][ny] != 0)
						continue;
					int tmp = visited[nx][ny];
					if (move[tmp] < i * M + j){
						move[tmp] = i * M + j;
						res += list.get(tmp);
					}
				}
				ans[i][j] = (res + 1) % 10;
			}
		}
		for(int i = 0; i < N; ++i){
			for(int j = 0; j < M; ++j){
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int x, int y, int num) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		visited[x][y] = num;
		int nx, ny;
		int cnt = 0;
		while (!q.isEmpty()) {
			++cnt;
			Pair tmp = q.poll();
			for (int i = 0; i < 4; ++i) {
				nx = tmp.x + dx[i];
				ny = tmp.y + dy[i];
				if (!isRange(nx, ny))
					continue;
				if (visited[nx][ny] != 0)
					continue;
				if (map[nx][ny] != 0)
					continue;
				q.add(new Pair(nx, ny));
				visited[nx][ny] = num;
			}
		}
		return cnt;
	}

	private static boolean isRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}
