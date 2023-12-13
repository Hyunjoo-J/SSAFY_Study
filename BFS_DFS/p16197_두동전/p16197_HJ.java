package BFS_DFS.p16197_두동전;

import java.util.*;
import java.io.*;
public class p16197_HJ {
	private static class Coin {
		int cx1, cy1, cx2, cy2;

		public Coin(int cx1, int cy1, int cx2, int cy2) {
			this.cx1 = cx1;
			this.cy1 = cy1;
			this.cx2 = cx2;
			this.cy2 = cy2;
		}
	}
	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cr1 = -1, cc1 = -1, cr2 = -1, cc2 = -1;
		int[][] map = new int[N][M];
		for(int i = 0; i < N; ++i){
			String str = br.readLine();
			for(int j = 0; j < M; ++j){
				if (str.charAt(j) == 'o') {
					if(cr1 == -1){
						cr1 = i;
						cc1 = j;
					}
					else{
						cr2 = i;
						cc2 = j;
					}
				}
				else if(str.charAt(j) == '#')
					map[i][j] = 1;
			}
		}
		boolean[][][][] visit = new boolean[N][M][N][M]; //각각의 동전이 해당 칸을 방문했는지
		Queue<Coin> q = new ArrayDeque<>();
		q.add(new Coin(cr1, cc1, cr2, cc2));
		visit[cr1][cc1][cr2][cc2] = true;

		boolean fin = false;
		int ans = 0;
		while(!q.isEmpty() && ans < 10){
			++ans;
			int size = q.size();
			next : for(int i = 0; i < size; ++i){
				Coin cur = q.poll();
				int nr1, nc1, nr2, nc2;
				for(int d = 0; d < 4; ++d){
					nr1 = cur.cx1 + dx[d];
					nc1 = cur.cy1 + dy[d];
					nr2 = cur.cx2 + dx[d];
					nc2 = cur.cy2 + dy[d];
					boolean flag1 = isRange(nr1, nc1, N, M);
					boolean flag2 = isRange(nr2, nc2, N, M);
					if(flag1 && map[nr1][nc1] == 1){
						nr1 -= dx[d];
						nc1 -= dy[d];
					}
					if(flag2 && map[nr2][nc2] == 1){
						nr2 -= dx[d];
						nc2 -= dy[d];
					}
					if(flag1 && flag2){
						if(!visit[nr1][nc1][nr2][nc2]){
							visit[nr1][nc1][nr2][nc2] = true;
							q.add(new Coin(nr1, nc1, nr2, nc2));
						}
					} else if (flag1 || flag2) {
						fin = true;
						q.clear();
						break next;
					}
				}
			}
		}
		System.out.println(fin ? ans : -1);
	}
	private static boolean isRange(int x, int y, int N, int M){
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
