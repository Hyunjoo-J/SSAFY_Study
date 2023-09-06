package TopologySort.p2623_음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2623_MJ {
	
	static class Node{
		int vertex;
		Node next;
		
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}	
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static Node adjList[];
	static int inDegree[];
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N+1];
		inDegree = new int[N+1];

		for(int pd = 1; pd < M+1; pd++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			for(int numS = 0; numS<num-1; numS++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
				from = to;
			}	
		}
		
		ArrayList<Integer> orderList = topologySort();
		
		if(orderList.size()==N) {
			for(int i=0; i<N; i++) {
				System.out.println(orderList.get(i));
			}
		}
		else {
			System.out.println(0);
		}
		
		br.close();
	}
	
	static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int i=1; i<N+1; i++) {
			if(inDegree[i]==0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			orderList.add(cur);
			
			for(Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
				if(--inDegree[tmp.vertex]==0) queue.offer(tmp.vertex);
			}	
		}
		
		return orderList;
	}
}
