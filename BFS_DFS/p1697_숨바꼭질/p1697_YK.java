package BFS_DFS.p1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1697_YK {
	static int N, K;
	static boolean visited[] = new boolean[100001];
	
	public static int bfs(int start) {
		visited[start] = true;
		Queue<int[]> q = new LinkedList<>();
		int[] a = {start, 0};
		q.add(a);
		
		while (!q.isEmpty()) {
			a = q.remove();
			if (a[0] == K) return a[1];
			
			if (a[0] < K) {
				if (a[0] * 2 < 100001 && !visited[a[0] * 2]) {
					visited[a[0] * 2] = true;
					int[] b = {a[0] * 2, a[1] + 1};
					q.add(b);
				}
				if (a[0] + 1 < 100001 && !visited[a[0] + 1]) {
					visited[a[0] + 1] = true;
					int[] b = {a[0] + 1, a[1] + 1};
					q.add(b);
				}
			}
			if (a[0] - 1 >= 0 && !visited[a[0] - 1]) {
				visited[a[0] - 1] = true;
				int[] b = {a[0] - 1, a[1] + 1};
				q.add(b);
			}
		}
		
		return -1;
	}

		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bw.write(String.valueOf(bfs(N)));

		br.close();
		bw.flush();
		bw.close();
	}
}