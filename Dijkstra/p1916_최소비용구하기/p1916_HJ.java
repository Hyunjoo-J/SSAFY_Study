package Dijkstra.p1916_최소비용구하기;
import java.util.*;
import java.io.*;
public class p1916_HJ {
	static class Node implements Comparable<Node>{
		int v, w;
		public Node(int v, int w){
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o){
			return Integer.compare(this.w, o.w);
		}
	}
	private static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		List<Node>[] graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();
		for(int i = 0, a, b, c; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()){
			Node cur = pq.poll();
			if(dist[cur.v] < cur.w)
				continue;
			int cost;
			for(Node next : graph[cur.v]){
				cost = cur.w + next.w;
				if(dist[next.v] > cost){
					dist[next.v] = cost;
					pq.add(new Node(next.v, cost));
				}
			}
		}
		System.out.println(dist[end]);
	}
}
