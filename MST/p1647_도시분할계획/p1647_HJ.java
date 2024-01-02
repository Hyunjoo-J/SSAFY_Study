package MST.p1647_도시분할계획;
import java.util.*;
import java.io.*;
public class p1647_HJ {
	private static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}


		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for(int i = 0; i <= N; ++i){
			parent[i] = i;
		}

		for(int i = 0, a, b, c; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a, b, c));
		}

		int res = 0;
		int cnt = 0;
		int max = 0;
		while (!pq.isEmpty()){
			Edge cur = pq.poll();
			if(union(cur.u, cur.v)){
				res += cur.w;
				if(max < cur.w)
					max = cur.w;
				if(++cnt == N - 1)
					break;
			}
		}
		System.out.println(res - max);

	}

	private static int find(int a){
		if(parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b){
		int ar = find(a);
		int br = find(b);
		if(ar == br)
			return false;
		parent[br] = ar;
		return true;
	}
}
