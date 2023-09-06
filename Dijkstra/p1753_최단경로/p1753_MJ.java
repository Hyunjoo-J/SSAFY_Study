package Dijkstra.p1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1753_MJ {

	static class Node implements Comparable<Node>{
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int V, E, K, u, v, w;
	static List<Node> list[];
	static int dist[];
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken()); 
			v = Integer.parseInt(st.nextToken()); 
			w = Integer.parseInt(st.nextToken()); 
			
			list[u].add(new Node(v, w));
		}
		
		dijkstra();
		for(int i=1; i<V+1; i++) {
			if(dist[i]==INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
		
		br.close();
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean visited[] = new boolean[V+1];
		queue.add(new Node(K, 0));
		dist[K] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.to;
			
			if(visited[cur]) continue;
			
			visited[cur] = true;
			
			for(Node node : list[cur]) {
				if(dist[node.to] > dist[cur] + node.weight) {
					dist[node.to] = dist[cur] + node.weight;
					queue.add(new Node(node.to, dist[node.to]));
				}	
			}	
		}
	}
}
