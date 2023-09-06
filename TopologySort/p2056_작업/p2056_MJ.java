package TopologySort.p2056_작업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2056_MJ {

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
	static int N, inDegree[], taskTime[], ansTime[]; 
	static Node adjList[];
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N+1];
		taskTime = new int[N+1];
		ansTime = new int[N+1];
		adjList = new Node[N+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			taskTime[i] = Integer.parseInt(st.nextToken());
			
			inDegree[i] = Integer.parseInt(st.nextToken());
			if(inDegree[i]==0)
				continue;
			
			for(int j=0; j<inDegree[i]; j++) {
				int from = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(i, adjList[from]);
			}
		}
		
		
		ArrayList<Integer> OrderList = topologySort(); 
		
		int max = -1;
		for(int i : OrderList) {
			if(max < ansTime[i])
				max = ansTime[i];
		}
		System.out.println(max);

		br.close();
	}
	
	static ArrayList<Integer> topologySort(){
		ArrayList<Integer> OrderList = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<N+1; i++) {
			if(inDegree[i]==0) {
				ansTime[i] = taskTime[i];
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			OrderList.add(cur);
			
			for(Node tmp=adjList[cur]; tmp!=null; tmp = tmp.next) {
				if(taskTime[tmp.vertex] + ansTime[cur] > ansTime[tmp.vertex])
					ansTime[tmp.vertex] = taskTime[tmp.vertex] + ansTime[cur];
				if(--inDegree[tmp.vertex]==0) {
					queue.offer(tmp.vertex);
				}
			}
		}
		
		return OrderList;
	}
}
