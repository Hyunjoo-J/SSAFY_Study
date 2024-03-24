package Bruteforce.프로그래머스LV2_42890_후보키;

import java.util.*;

class Solution_HC {

	private int n, m;
	private int[] uniqueKeys = new int[555];
	private int uniqueKeyCount = 0;
	private String[][] relation;

	public int solution(String[][] relation) {
		this.relation = relation;
		n = relation.length;
		m = relation[0].length;
		powerSet(0, 0);
		return countCandidateKey();
	}

	private int countCandidateKey() {
		Arrays.sort(uniqueKeys, 0, uniqueKeyCount);
		boolean[] checker = new boolean[uniqueKeyCount];
		Arrays.fill(checker, true);
		for (int i = 0; i < uniqueKeyCount; ++i) {
			for (int j = i + 1; j < uniqueKeyCount; ++j) {
				if ((uniqueKeys[i] & uniqueKeys[j]) == uniqueKeys[i])
					checker[j] = false;
			}
		}

		int count = 0;
		for (boolean b: checker) {
			count += b ? 1 : 0;
		}
		return count;
	}

	private void powerSet(int depth, int visit) {
		if (depth == m) {
			if (visit == 0)
				return;

			if (isUniqueKey(visit)) {
				uniqueKeys[uniqueKeyCount++] = visit;
			}
			return;
		}

		powerSet(depth + 1, visit | (1 << depth));
		powerSet(depth + 1, visit);
	}

	private boolean isUniqueKey(int visit) {
		Set<String> set = new HashSet<>();
		for (int row = 0; row < n; ++row) {
			StringBuilder sb = new StringBuilder();
			for (int col = 0; col < m; ++col) {
				if ((visit & (1 << col)) > 0)
					sb.append(relation[row][col]).append(" ");
			}
			set.add(sb.toString());
		}
		return set.size() == n;
	}
}