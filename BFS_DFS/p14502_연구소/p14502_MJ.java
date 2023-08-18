package BFS_DFS.p14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p14502_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], mapTmp[][], wall, cnt, min = Integer.MAX_VALUE;
	static Queue<Integer[]> queue = new LinkedList<Integer[]>();
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	
	static void bfs() {
		
		// 맵을 수정하기 위해 mapTmp를 만들어 기존 map 복제
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				mapTmp[i][j] = map[i][j];
			}
		}
		
		// 바이러스 영역(위치)를 queue에 넣어줌
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(mapTmp[i][j]==2) {
					queue.offer(new Integer[] {i, j});
				}
			}
		}
		
		// cnt : 바이러스 영역의 총 개수
		// queue.size(현재 바이러스 영역 개수)를 cnt에 넣어줌
		cnt = queue.size();
		
		while(!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			queue.poll();
			
			for(int j=0; j<4; j++) {
				int nowX = x + dx[j];
				int nowY = y + dy[j];
				
				if(nowX<0||nowY<0||nowX>=N||nowY>=M)
					continue;
				
				if(mapTmp[nowX][nowY]==1) {
					continue;
				}
				
				//바이러스 영역 옆에 0(빈칸)이 있으면 2(바이러스)로 바꿔주고 cnt를 증가시킴
				if(mapTmp[nowX][nowY]==0) {
					mapTmp[nowX][nowY] = 2;
					queue.offer(new Integer[] {nowX, nowY});
					cnt++;
				}
			}
		}
		
		// 바이러스가 퍼진 영역의 최소값을 찾음
		if(cnt<min) {
			min = cnt;
		}
	}
	
	// 0이 있는 위치들 중 3개를 고른 조합
	static void combi(int cnt) {
		if(cnt==3) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j] = 1; 
					combi(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		mapTmp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					wall++;
			}
		}
		
		combi(0);

		// 최대 안전 영역 = 전체 사이즈 - 바이러스가 퍼진 영역의 최소 개수 - 기존 벽의 개수 - 3(새로 생긴 벽의 수)
		System.out.println(N*M-min-wall-3);
		
		br.close();
	}	
}
