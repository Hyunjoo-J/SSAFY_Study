package BFS_DFS.p2606_바이러스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 바이러스
public class p2606_MJ {
	
	static ArrayList<Integer>[] array;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int comNum = Integer.parseInt(br.readLine());
		int lineNum = Integer.parseInt(br.readLine());
		
		visited = new boolean[comNum+1];
		
		// 인접리스트 초기화
		array = new ArrayList[comNum+1];
		for(int i=1; i<comNum+1; i++) {
			array[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st;
		for(int i=0; i<lineNum; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			array[start].add(end);
			array[end].add(start);
		}
		
		dfs(1);
		
		int ans = 0; // 바이러스에 걸린 컴퓨터 수
		for(int i=2; i<comNum+1; i++) {
			if(visited[i]==true)
				ans++;
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	static void dfs(int num) {
		visited[num] = true;
		for(int i : array[num]) {
			if(!visited[i])
				dfs(i);
		}
	}
}