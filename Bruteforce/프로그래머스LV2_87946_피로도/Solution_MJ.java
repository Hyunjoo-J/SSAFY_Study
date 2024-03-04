package Bruteforce.프로그래머스LV2_87946_피로도;

import java.util.*;
class Solution_MJ {
	static int answer, length;
	static boolean[] visited;
	public int solution(int k, int[][] dungeons) {
		answer = 0;
		length = dungeons.length;

		visited = new boolean[length];
		dfs(0, k, dungeons);

		return answer;
	}

	// cnt: 탐험할 수 있는 던전 수, k: 현재 피로도, dungeons: 던전 정보
	private void dfs(int cnt, int k, int[][] dungeons){
		for(int i=0; i<length; ++i){
			// 1) 방문을 안했고 2) 최소 필요 피로도보다 현재 k가 크거나 같을 때
			if(!visited[i]&&k>=dungeons[i][0]){
				visited[i] = true;
				dfs(cnt+1, k-dungeons[i][1], dungeons);
				visited[i] = false;
			}
		}
		answer = Math.max(cnt, answer);
	}
}