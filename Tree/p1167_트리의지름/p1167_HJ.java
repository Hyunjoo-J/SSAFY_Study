package Tree.p1167_트리의지름;

import java.util.*;
import java.io.*;
public class p1167_HJ {
	static class Node{
		int v, w;
		Node(int v, int w){
			this.v = v;
			this.w = w;
		}
	}
	private static int V, node, max = 0;
	private static ArrayList<Node>[] tree;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		tree = new ArrayList[V + 1];
		for(int i = 0; i <= V; ++i)
			tree[i] = new ArrayList<>();
		visited = new boolean[V + 1];

		int a, b, c;
		for(int i = 0; i < V; ++i){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			while(true){
				b = Integer.parseInt(st.nextToken());
				if(b == -1)
					break;
				c = Integer.parseInt(st.nextToken());
				tree[a].add(new Node(b, c));
			}
		}

		dfs(1, 0);

		visited = new boolean[V + 1];
		dfs(node, 0);
		System.out.println(max);
	}

	private static void dfs(int v, int len){
		if(len > max){
			max = len;
			node = v;
		}
		visited[v] = true;
		for(Node n : tree[v]){
			if(!visited[n.v]){
				dfs(n.v, len + n.w);
				visited[n.v] = true;
			}
		}
	}
}
