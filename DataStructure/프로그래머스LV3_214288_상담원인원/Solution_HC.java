package DataStructure.프로그래머스LV3_214288_상담원인원;

import java.util.*;

class Solution_HC {

	private int k, n;
	private int[] mentor = new int[6];
	private int[][] reqs;
	private int answer = 1234567890;

	public int solution(int k, int n, int[][] reqs) {
		this.k = k;
		this.n = n;
		this.reqs = reqs;

		dfs(n, 0);
		return answer;
	}

	private int simul() {
		Queue<Integer>[] heaps = new PriorityQueue[k + 1];
		for (int i = 1; i <= k; ++i) {
			heaps[i] = new PriorityQueue<>();
		}

		int res = 0;
		for (int[] req: reqs) {
			Queue<Integer> heap = heaps[req[2]];
			while (!heap.isEmpty() && heap.peek() < req[0]) {
				heap.poll();
			}

			if (heap.size() < mentor[req[2]]) {
				heap.add(req[0] + req[1]);
			} else {
				int endTime = heap.poll();
				res += (endTime - req[0]);
				heap.add(endTime + req[1]);
			}
		}
		return res;
	}

	private void dfs(int n, int depth) {
		if (depth == k) {
			if (n == 0) {
				answer = Math.min(answer, simul());
			}
			return;
		}
		for (int i = 1; i <= n; ++i) {
			mentor[depth + 1] = i;
			dfs(n - i, depth + 1);
		}
	}
}