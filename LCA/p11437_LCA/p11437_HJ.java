package LCA.p11437_LCA;// https://www.acmicpc.net/problem/11437
import java.util.*;
import java.io.*;

public class p11437_HJ {
	static int N, M;
	static int[] depth;
	static int[] parent;
	static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		parent = new int[N + 1];
		graph = new ArrayList<>();
		for(int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}
		int a, b;
		for(int i= 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		getDepth(1, 1);
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int lca(int a, int b) {
		if(depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		while(depth[a] != depth[b]) { //높이 맞춰주기
			a = parent[a];
		}
		while(a != b) {//동시에 한칸씩 올라옴
			a = parent[a];
			b = parent[b];
		}
		return a;
	}

	private static void getDepth(int cur, int dep) {
		depth[cur] = dep;
		for(int next : graph.get(cur)) {
			if(depth[next] == 0) {
				parent[next] = cur;
				getDepth(next, dep + 1);
			}
		}

	}

}