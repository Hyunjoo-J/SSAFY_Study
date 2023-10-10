package p14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14889_MJ {

	static int N;
	static int[][] team;
	static int min = 1000000;
	static int start;
	static int link;
	static boolean[] visited;
	
	static void input() throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     N = Integer.parseInt(br.readLine());
	     StringTokenizer st;
	        
	     team = new int[N][N];
	        
	     for(int i=0; i<N; i++) {
	     	st = new StringTokenizer(br.readLine());
	     	for(int j=0; j<N; j++) {
	     		team[i][j] = Integer.parseInt(st.nextToken());
	     	}
	     }
	     
	     visited = new boolean[N];
	     	
	}
	
	static void comb(int[][] team, boolean[] visited, int now, int n, int r) {
		
		if(r == 0) {
			start = 0;
			link = 0;
			for(int i=0; i<N; i++) {
				for(int j=i; j<N; j++) {
					if(visited[i] == true && visited[j] == true) {
						start += team[i][j] + team[j][i];
					}
					else if(visited[i] == false && visited[j] == false) {
						link += team[i][j] + team[j][i];
					}
				}
			}
			if(min > Math.abs(start - link))
				min = Math.abs(start - link);
			return;
		}
		
		for(int i = now; i < n; i++) {
			visited[i] = true;
			comb(team, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		comb(team, visited, 0, N, N/2);
		System.out.println(min);
	}

}
