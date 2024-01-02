package Dijkstra.p16118_달빛여우;
import java.util.*;
import java.io.*;
public class p16118_HJ {
	static class Node {
		int v, w, d;
		public Node(int v, int w){
			this.v = v;
			this.w = w;
		}

		public Node(int v, int w, int d){
			this.v = v;
			this.w = w;
			this.d = d;
		}
	}
	private static final int INF = Integer.MAX_VALUE;
	private static int N, M;
	private static List<Node>[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		for(int i = 0, a, b, d; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) << 1;
			graph[a].add(new Node(b, d));
			graph[b].add(new Node(a, d));
		}

		int[] distFox = dijkstraFox(1);
		int[] distWolf = dijkstraWolf(1);

		int ans = 0;
		for(int i = 1; i <= N; ++i){
			if(distFox[i] < distWolf[i])
				++ans;
		}

		System.out.println(ans);
	}

	private static int[] dijkstraFox(int cur){
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		pq.add(new Node(cur, 0));
		dist[cur] = 0;
		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(dist[now.v] < now.w)
				continue;
			for(Node next : graph[now.v]){
				int cost = now.w + next.w;
				if(dist[next.v] > cost){
					dist[next.v] = cost;
					pq.add(new Node(next.v, cost));
				}
			}
		}
		return dist;
	}

	private static int[] dijkstraWolf(int cur){
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		int[][] dist = new int[N + 1][2];
		for(int i = 0; i <= N; ++i){
			Arrays.fill(dist[i], INF);
		}
		pq.add(new Node(cur, 0, 1));
		dist[cur][1] = 0;

		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(dist[now.v][now.d] < now.w)
				continue;
			for(Node next : graph[now.v]){
				//홀수 짝수 속도 조절
				int cost = now.w + (now.d == 1 ? next.w / 2 : next.w * 2);
				if(dist[next.v][1 - now.d] > cost){
					dist[next.v][1 - now.d] = cost;
					pq.add(new Node(next.v, cost, 1 - now.d));
				}
			}
		}
		int[] res = new int[N + 1];
		for(int i = 0; i <= N; ++i)
			res[i] = Math.min(dist[i][0], dist[i][1]);
		return res;
	}
}
