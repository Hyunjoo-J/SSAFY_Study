package BFS_DFS.p11724_연결요소의개수;
import java.util.*;
import java.io.*;
public class p11724_HJ {
	static int N, M;
	static int[][] arr;
	static boolean[] visited;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt((st.nextToken()));
		M = Integer.parseInt((st.nextToken()));
		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for(int i = 0; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[u][v] = arr[v][u] = 1;
		}
		for(int i = 1; i <= N; ++i){
			if(visited[i])
				continue;
			dfs(i);
			++ans;
		}
		System.out.println(ans);
	}

	private static void dfs(int cur){
		if(visited[cur])
			return;
		visited[cur] = true;
		for(int i = 1; i <= N; ++i){
			if(arr[cur][i] == 1)
				dfs(i);
		}
	}
}
