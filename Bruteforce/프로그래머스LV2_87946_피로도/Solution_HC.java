package Bruteforce.프로그래머스LV2_87946_피로도;

class Solution_HC {

	public int solution(int k, int[][] dungeons) {
		int answer = -1;
		int n = dungeons.length;
		int[] order = new int[n];
		for (int i = 0; i < n; ++i) {
			order[i] = i;
		}

		do {
			answer = Math.max(answer, test(k, n, dungeons, order));
		} while (np(order, n));

		return answer;
	}

	private static int test(int k, int n, int[][] dungeons, int[] order) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			int[] dungeon = dungeons[order[i]];
			if (k < dungeon[0])
				break;
			k -= dungeon[1];
			++count;
		}
		return count;
	}

	private static boolean np(int[] perm, int n) {
		int i = n - 1;
		while (i > 0 && perm[i - 1] > perm[i])
			--i;

		if (i == 0)
			return false;

		int j = n - 1;
		while (perm[i - 1] > perm[j])
			--j;
		swap(perm, i - 1, j);

		int k = n - 1;
		while (i < k)
			swap(perm, i++, k--);

		return true;
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}