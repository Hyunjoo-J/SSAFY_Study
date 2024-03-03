import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, T;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static int[][] map;
	static int answer;
	static boolean flag;

	public static class Node{
		int x, y, knife, count;

		public Node(int x, int y, int knife, int count) {
			this.x = x;
			this.y = y;
			this.knife = knife;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		if(flag && answer <= T){
			System.out.println(answer);
		}else{
			System.out.println("Fail");
		}
	}

	public static void bfs(){

		Deque<Node> dq = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		dq.add(new Node(0 , 0, 0, 0));
		/**
		 * visited 3차원
		 */
		visited[0][0][0] = true;

		while(!dq.isEmpty()){

			Node now = dq.poll();

			/**
			 * now.y == M-1로 수정 
			 */
			if(now.x == N-1 && now.y == M-1){
				answer = now.count;
				flag = true;
				return;
			}

			/**
			 * knife로 벽을 부수는 제한없음
			 */
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int nKnife = now.knife;

				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (map[nx][ny] == 2)
					nKnife = 1;
				if(visited[nx][ny][nKnife]) continue;

				if(map[nx][ny] == 0){
					dq.add(new Node(nx, ny, nKnife, now.count+1));
					visited[nx][ny][nKnife] = true;
				}else if(map[nx][ny] == 2){
					dq.add(new Node(nx, ny, nKnife, now.count+1));
					visited[nx][ny][nKnife] = true;
				}else{
					if(nKnife > 0){
						dq.add(new Node(nx, ny, nKnife, now.count+1));
						visited[nx][ny][nKnife] = true;
					}
				}
			}
		}
	}
}