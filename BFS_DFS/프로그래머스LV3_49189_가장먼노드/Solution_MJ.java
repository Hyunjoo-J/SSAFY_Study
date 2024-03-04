package BFS_DFS.프로그래머스LV3_49189_가장먼노드;

import java.util.*;
public class Solution_MJ {
	static List<Integer>[] list;
	public int solution(int n, int[][] edge) {
		int answer = 0;

		list = new ArrayList[n+1];
		for(int i=1; i<=n; ++i){
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<edge.length; ++i){
			int a = edge[i][0];
			int b = edge[i][1];
			list[a].add(b);
			list[b].add(a);
		}

		answer = bfs(1, n);

		return answer;
	}

	private int bfs(int idx, int n){
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		visited[1] = true;
		for(int i=0; i<list[idx].size(); ++i){
			int next = list[idx].get(i);
			queue.offer(next);
			visited[next] = true;
		}

		int size = 0;
		while(!queue.isEmpty()){
			size = queue.size();
			for(int qs=0; qs<size; ++qs){
				int now = queue.poll();
				for(int i=0; i<list[now].size(); ++i){
					int next = list[now].get(i);
					if(!visited[next]){
						queue.offer(next);
						visited[next] = true;
					}
				}
			}
		}

		return size;
	}
}