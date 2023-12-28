package BFS_DFS.p7562_나이트의이동;

import java.util.*;
import java.io.*;
public class p7562_HJ {
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
	static int I;
	static int[][] map;
	static int x1, y1, x2, y2;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0){
			I = Integer.parseInt(br.readLine());
			map = new int[I][I];
			visited = new boolean[I][I];
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			bfs();

			sb.append(map[x2][y2]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(){
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x1, y1));
		visited[x1][y1] = true;
		while (!q.isEmpty()){
			Pair cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			for(int i = 0; i < 8; ++i){
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(nx < 0 || ny < 0 || nx >= I || ny >= I)
					continue;
				if(visited[nx][ny])
					continue;
				q.add(new Pair(nx, ny));
				visited[nx][ny] = true;
				map[nx][ny] = map[cx][cy] + 1;
			}
		}
	}
}
