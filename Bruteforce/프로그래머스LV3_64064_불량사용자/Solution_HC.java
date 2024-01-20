package Bruteforce.프로그래머스LV3_64064_불량사용자;

import java.util.*;

class Solution_HC {

	private int n, r;
	private int[] perm;
	private String[] userIds;
	private String[] bannedIds;
	private Set<String> answerSet = new HashSet<>();

	public int solution(String[] user_id, String[] banned_id) {
		userIds = user_id;
		bannedIds = banned_id;
		n = user_id.length;
		r = banned_id.length;
		perm = new int[r];
		dfs(0, 0);

		return answerSet.size();
	}

	private void dfs(int depth, int visit) {
		if (depth == r) {
			String[] copied = new String[r];
			for (int i = 0; i < r; ++i) {
				if (!match(userIds[perm[i]], bannedIds[i]))
					return;
				copied[i] = userIds[perm[i]];
			}

			Arrays.sort(copied);
			StringBuilder sb = new StringBuilder();
			for (String userId: copied)
				sb.append(userId).append(" ");
			answerSet.add(sb.toString());
			return;
		}
		for (int i = 0; i < n; ++i) {
			if ((visit & (1 << i)) > 0)
				continue;
			perm[depth] = i;
			dfs(depth + 1, visit | (1 << i));
		}
	}

	private boolean match(String userId, String mask) {
		int n = userId.length();
		int m = mask.length();
		if (n != m)
			return false;

		for (int i = 0; i < n; ++i) {
			if (mask.charAt(i) == '*')
				continue;
			if (userId.charAt(i) != mask.charAt(i))
				return false;
		}
		return true;
	}
}