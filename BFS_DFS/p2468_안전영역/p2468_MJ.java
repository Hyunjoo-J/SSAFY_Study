package BFS_DFS.p2468_안전영역;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2468_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] region;
	static int N, max=0, count, countMax=0;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	static boolean[][] visited;
	
	static void find(int x, int y, int rain) {
		int nowX, nowY;
		
		for(int i=0; i<4; i++) {
			nowX = x + dx[i];
			nowY = y + dy[i];
			
			// 상하좌우 중 배열을 벗어나는 값은 미리 제외
			if(nowX>-1&&nowY>-1&&nowX<N&&nowY<N) {
				// 이미 방문한 곳 or rain보다 작거나 같은 값은 고려할 필요가 없으니 continue
				if(visited[nowX][nowY]==true||region[nowX][nowY]<rain+1) {
					continue;
				}
				else {
					visited[nowX][nowY] = true;
					// 현재 위치와 이어진 곳들을 전부 파악하기 위해 재귀 사용
					find(nowX, nowY, rain);
				}
			}			
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		region = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				region[i][j] = Integer.parseInt(st.nextToken());
				if(max<region[i][j])
					max = region[i][j];
			}
		}
		
		// 비가 0부터 max까지 온 경우의 수
		for(int i=0; i<max+1; i++) {
			count = 0;
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(region[x][y]>i&&visited[x][y]==false) {
						// 안전 영역 개수 구하기
						visited[x][y]=true;
						find(x,y,i);
						count++;
					}
				}
			}
			
			if(count>countMax)
				countMax = count;
			
			// 다시 visited 초기화
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					visited[x][y]=false;
				}
			}
		}
			
		System.out.println(countMax);
		
		br.close();
	}
}
