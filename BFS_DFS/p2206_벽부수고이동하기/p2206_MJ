import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], maptmp[][], cnt, min = Integer.MAX_VALUE;
	static boolean visited[][];
	static Queue<Integer[]> wall = new LinkedList<Integer[]>();
	static Queue<Integer[]> queue = new LinkedList<Integer[]>();
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	static void bfs(){
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				maptmp[i][j] = map[i][j];
				visited[i][j] = false;
			}
		}
		
		visited[0][0] = true;
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(maptmp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		while(!queue.isEmpty()) {
			cnt++;
			int queueSize = queue.size();
			for(int i=0; i<queueSize; i++) {
				int x = queue.peek()[0];
				int y = queue.peek()[1];
				queue.poll();
				
				System.out.println(x + " " + y);
				
				for(int j=0; j<4; j++) {
					int nowX = x + dx[j];
					int nowY = y + dy[j];
					
					
					if(nowX<0||nowY<0||nowX>=N||nowY>=M)
						continue;
					
					if(maptmp[nowX][nowY]==1)
						continue;
					
					if(visited[nowX][nowY])
						continue;
					
					if(nowX == N-1 && nowY == M-1) {
						return;
					}
					
					queue.offer(new Integer[] {nowX, nowY});
					visited[nowX][nowY] = true;
				}	
			}
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		maptmp = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String tmpS = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = tmpS.charAt(j)-'0';
				if(map[i][j]==1) {
					wall.offer(new Integer[] {i, j});
				}
			}
		}
		
		for(int i=0; i<wall.size(); i++) {
			int x = wall.peek()[0];
			int y = wall.peek()[1];
			wall.poll();
			
			map[x][y] = 0;
			queue.offer(new Integer[] {0, 0});
			visited[0][0] = true;
			cnt = 0;
			bfs();
			if(cnt>1&&cnt<min) {
				min = cnt;
			}
			map[x][y] = 1;

		}
		
		if(cnt==1)
			min = -1;
		
		System.out.println(min);

		br.close();
	}
}
