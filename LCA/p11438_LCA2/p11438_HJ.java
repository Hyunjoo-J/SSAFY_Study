package LCA.p11438_LCA2;
import java.util.*;
import java.io.*;
public class p11438_HJ {
	static int N, M;
	static int[][] parent;
	static int[] depth;
	static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		parent = new int[18][N + 1]; //parent[a][b] = b노드의 2^a번째 부모
		graph = new ArrayList<>();
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}
		int a, b;
		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		getDep(1, 1);
		getParent();
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b) + "\n");
		}
		System.out.print(sb.toString());

	}

	private static int lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		for (int i = 17; i >= 0; --i) {
			if (depth[a] - (1 << i) >= depth[b]) {
				a = parent[i][a]; //해당 노드(a)의 2^i번째 조상
			}
			if (depth[a] == depth[b]) //dep가 같으면 탈출
				break;
		}
		int res = a;
		if(a != b){//하나씩 올라가면서 맞추기
			for(int i  = 17; i >= 0; --i){
				if(parent[i][a] != parent[i][b]){ //가장 최상단부터 돌면서 첫번째로 같아지는것을 찾음
					a = parent[i][a];
					b = parent[i][b];
				}
			}
			res = parent[0][a];
		}
		return res;
	}

	private static void getParent() {
		for (int i = 1; i <= 17; ++i) { //0번째는 이미 getdep에서 채움
			for (int j = 1; j <= N; ++j) {//부모의 바로 위가 가르키고 있는 것
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
			}
		}
	}

	private static void getDep(int cur, int dep) {
		depth[cur] = dep;
		for (int next : graph.get(cur)) {
			if (depth[next] == 0) {
				parent[0][next] = cur; //next의 바로 위 부모는 cur이다
				getDep(next, dep + 1);
			}
		}
	}
}