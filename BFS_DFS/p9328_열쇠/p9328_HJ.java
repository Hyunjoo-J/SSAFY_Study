package BFS_DFS.p9328_열쇠;

import java.util.*;
import java.io.*;
public class p9328_HJ {
	private static class Pair{
		int x, y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, -1, 0, 1};
	private static char[][] map;
	private static boolean[] key; //알파벳
	private static List<Pair>[] gate;
	private static boolean[][] visited;
	private static int h, w;
	private static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h + 2][w + 2];
			visited = new boolean[h + 2][w + 2];
			key = new boolean[26];
			gate = new ArrayList[26];

			cnt = 0;

			for(int i = 0; i < 26; ++i){
				gate[i] = new ArrayList<>();
			}

			for(int i = 0; i < h + 2; ++i){
				for(int j = 0; j < w + 2; ++j){
					map[i][j] = '.';
				}
			}

			for(int i = 1; i <= h; ++i){
				String line = br.readLine();
				for(int j = 1; j <= w; ++j){
					map[i][j] = line.charAt(j - 1);
				}
			}

			String line = br.readLine();
			if(!line.equals("0")){
				for(int i = 0; i < line.length(); ++i){
					int tmp = line.charAt(i) - 'a';
					key[tmp] = true;
				}
			}

			bfs();
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(){
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(0, 0));
		visited[0][0] = true;
		while(!q.isEmpty()){
			Pair cur = q.poll();
			for(int i = 0; i < 4; ++i){
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2)
					continue;

				if (map[nx][ny] == '*' || visited[nx][ny])
					continue;

				int ch = map[nx][ny];
				if(ch - 'A' >= 0 && ch - 'A' <= 25){
					//문 발결
					if(key[ch - 'A']){
						map[nx][ny] = '.';
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny));
					}else{
						gate[ch - 'A'].add(new Pair(nx, ny));
					}
				}else if(ch - 'a' >= 0 && ch - 'a' <= 25){
					//열쇠 발견
					key[ch - 'a'] = true;
					visited[nx][ny] = true;
					q.add(new Pair(nx, ny));

					for(int j = 0; j < 26; ++j){
						if(gate[j].size() != 0 && key[j]){
							for(int k = 0; k < gate[j].size(); ++k){
								Pair tmp = gate[j].get(k);
								map[tmp.x][tmp.y] = '.';
								visited[tmp.x][tmp.y] = true;
								q.add(new Pair(tmp.x, tmp.y));
							}
						}
					}
				} else if(ch == '$'){
					++cnt;
					visited[nx][ny] = true;
					q.add(new Pair(nx, ny));
				} else{
					visited[nx][ny] = true;
					q.add(new Pair(nx, ny));
				}
			}
		}
	}
}
