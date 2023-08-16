import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, K, cnt, ans;
	static boolean map[][];
	static int dx[] = {0, 0, 1, -1};
	static int dy[] = {1, -1, 0, 0};
	
	static void dfs(int x, int y) {
		cnt++;
		if(x==0 && y==C-1) {
			if(cnt==K) {
				ans++;
			}
			return;
		}
		
		for(int j=0; j<4; j++) {
			int nowX = x + dx[j];
			int nowY = y + dy[j];
			
			if(nowX<0||nowY<0||nowX>=R||nowY>=C)
				continue;
		
			if(!map[nowX][nowY]) {
				map[nowX][nowY] = true;
				dfs(nowX, nowY);
				map[nowX][nowY] = false;
				cnt--;
			}
		}	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map  = new boolean[R][C];
		
		for(int i=0;i<R; i++) {
			String tmp = br.readLine();
			for(int j=0; j<C; j++) {
				if(tmp.charAt(j)=='T') {
					map[i][j] = true;
				}
			}
		}
		
		map[R-1][0] = true;
		dfs(R-1, 0);
		
		System.out.println(ans);
	}
}
