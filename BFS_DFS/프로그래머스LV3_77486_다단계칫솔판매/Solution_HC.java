package BFS_DFS.프로그래머스LV3_77486_다단계칫솔판매;

import java.util.*;

class Solution_HC {

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		Map<String, Integer> map = new HashMap<>();
		compression(map, enroll);
		compression(map, referral);
		map.put("-", -1);

		int n = map.size();
		int[] parent = new int[n];
		int[] result = new int[enroll.length];

		for (int i = 0, end = enroll.length; i < end; ++i) {
			int e = map.get(enroll[i]);
			int r = map.get(referral[i]);
			parent[e] = r;
		}

		for (int i = 0, end = seller.length; i < end; ++i) {
			sell(parent, result, map.get(seller[i]), amount[i] * 100);
		}
		return result;
	}

	private void sell(int[] parent, int[] result, int sellerNum, int money) {
		int tenPercent = (int) (money * 0.1);
		result[sellerNum] += money - tenPercent;
		if (parent[sellerNum] >= 0 && tenPercent > 0) {
			sell(parent, result, parent[sellerNum], tenPercent);
		}
	}

	private void compression(Map<String, Integer> map, String[] arr) {
		int idx = map.size();
		for (String name: arr) {
			if (map.containsKey(name))
				continue;
			map.put(name, idx++);
		}
	}
}