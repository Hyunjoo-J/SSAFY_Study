package Bruteforce.프로그래머스LV2_양궁대회;

class Solution_HC {

	private int[] apeach;
	private int[] ryan = new int[11];
	private int[] answer = null;
	private int maxDiff = 0;
	private boolean findAnswer = false;

	private int compareRyanAndApeach(int[] ryan, int[] apeach) {
		int ryanScore = 0;
		int apeachScore = 0;
		for (int i = 0; i <= 10; ++i) {
			if (apeach[i] == 0 && ryan[i] == 0)
				continue;
			if (apeach[i] >= ryan[i]) {
				apeachScore += 10 - i;
			} else {
				ryanScore += 10 - i;
			}
		}
		return ryanScore - apeachScore;
	}

	private void compareAnswerAndUpdate(int[] array) {
		if (answer == null) {
			answer = array.clone();
			return;
		}
		for (int i = 10; i >= 0; --i) {
			if (answer[i] < array[i]) {
				answer = array.clone();
				break;
			} else if (answer[i] > array[i]) {
				break;
			}
		}
	}

	private void dfs(int depth, int count) {
		if (depth > 10) {
			if (count > 0)
				return;

			int diff = compareRyanAndApeach(ryan, apeach);
			if (maxDiff < diff) {
				findAnswer = true;
				maxDiff = diff;
				answer = ryan.clone();
			} else if (maxDiff == diff) {
				compareAnswerAndUpdate(ryan);
			}
		} else {
			for (int i = 0; i <= count; ++i) {
				ryan[depth] = i;
				dfs(depth + 1, count - i);
				ryan[depth] = 0;
			}
		}
	}

	public int[] solution(int n, int[] info) {
		apeach = info;
		dfs(0, n);
		return findAnswer ? answer : new int[]{-1};
	}
}