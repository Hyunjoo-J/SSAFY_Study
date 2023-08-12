package BFS_DFS.p12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p12851_YK {
	static int N, K;
	static int R1, R2;
	static int depth;
	static boolean[] visited;
	static final int MAX = 100001;

	public static void bfs(int start) {
		Queue<int[]> q = new LinkedList<>();
		int[] a = { start, 0 };
		q.add(a);

		while (!q.isEmpty()) {
			int qSize = q.size();
			
			for (int i = 0; i < qSize; ++i) {
				a = q.remove();
				visited[a[0]] = true;
				
				if (a[0] == K) {
					if (R1 == 0) {
						R1 = a[1];
					}
					if (a[1] == R1) {
						R2 += 1;
					} else {
						return;
					}
				}
				
				if (a[0] < K) {
					if (a[0] * 2 < MAX && !visited[a[0] * 2]) {
						int[] b = { a[0] * 2, a[1] + 1 };
						q.add(b);
					}
					if (a[0] + 1 < MAX && !visited[a[0] + 1]) {
						int[] b = { a[0] + 1, a[1] + 1 };
						q.add(b);
					}
				}
				if (a[0] - 1 >= 0 && !visited[a[0] - 1]) {
					int[] b = { a[0] - 1, a[1] + 1 };
					q.add(b);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[MAX];

		bfs(N);
		bw.write(String.valueOf(R1));
		bw.write("\n");
		bw.write(String.valueOf(R2));

		br.close();
		bw.flush();
		bw.close();
	}
}