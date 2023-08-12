package BFS_DFS.p2644_촌수계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 촌수계산
public class p2644_MJ {

	static ArrayList<Integer> array[];
	static boolean visited[];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int perNum = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int one = Integer.parseInt(st.nextToken()); 
		int two = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int lineNum = Integer.parseInt(st.nextToken());
		
		// 초기화
		visited = new boolean[perNum+1];
		array = new ArrayList[perNum+1];
		
		for(int i=1; i<perNum+1; i++) {
			array[i] = new ArrayList();
		}
		
		for(int i=0; i<lineNum; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			array[start].add(end);
			array[end].add(start);
		}
		
		// ans을 -1(촌수 계산이 안되는 경우)로 설정
		ans = -1;
		int depth = 0;
		dfs(one, two, depth);
		
		System.out.println(ans);
	
		br.close();
	}
	
	static void dfs(int one, int two, int depth) {
		
		visited[one] = true;
		// one == two : 촌수 계산할 사람을 만났을 때 
		if(one == two) {
			ans = depth;
			return;
		}
			
		for(int i : array[one]) {
			if(!visited[i]) {
				// depth+1 <- 깊이가 깊어질 때를 표시해주기 위해(깊이가 깊어질 때마다 촌수가 증가함)
				dfs(i, two, depth+1);
			}
		}
	}
}