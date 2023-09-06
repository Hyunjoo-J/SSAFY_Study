package Dijkstra.p10282_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p10282_MJ {

	static class Node implements Comparable<Node>{
		int to;
		int time;
		
		public Node(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node e) {
			return Integer.compare(this.time, e.time);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, d, c, a, b, s;
	static int dist[];
	static ArrayList<Node> list[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			list = new ArrayList[n+1];
			for(int i=0; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
		
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Node(a, s));
			}
			
			dijkstra(c);
			
			int max = 0;
			int cnt = 0;
			for(int i=1; i<n+1; i++) {
				if(dist[i]==Integer.MAX_VALUE)
					continue;
				if(max < dist[i])
					max = dist[i];
				cnt++;
			}
			
			System.out.println(cnt + " " + max);
		}
		
		br.close();
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean visited[] = new boolean[n+1];
		queue.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.to;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			for(Node node : list[cur]) {
				if(dist[node.to] > dist[cur] + node.time) {
					dist[node.to] = dist[cur] + node.time;
					queue.add(new Node(node.to, dist[node.to]));
				}
			}
		}
	}
}
