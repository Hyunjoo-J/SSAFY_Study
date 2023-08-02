package BFS.p2606_바이러스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2606_YK {
	static int E, V;
	static boolean[][] arr;
	static boolean[] visited;
	
	public static int bfs(int start) {
		visited[start] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		int r = 0;
		
		while (!q.isEmpty()) {
			int a = q.remove();
			for (int i = 1; i <= E; i++) {
				if (!visited[i] && arr[a][i]) {
					visited[i] = true;
					q.add(i);
					r += 1;
				}
			}
		}
		
		return r;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		E = Integer.parseInt(br.readLine());
		V = Integer.parseInt(br.readLine());
		
		arr = new boolean[E + 1][E + 1];
		visited = new boolean[E + 1];
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = true;
			arr[y][x] = true;
		}
		
		bw.write(String.valueOf(bfs(1)));
		
		br.close();
		bw.close();
	}
}
