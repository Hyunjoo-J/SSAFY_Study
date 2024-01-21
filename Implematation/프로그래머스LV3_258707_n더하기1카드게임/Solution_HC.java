package Implematation.프로그래머스LV3_258707_n더하기1카드게임;

import java.util.*;

class Solution_HC {

	public int solution(int coin, int[] cards) {
		Set<Integer> onHand = new HashSet<>();
		Set<Integer> discarded = new HashSet<>();

		int n = cards.length;
		int target = n + 1;
		int i = 0;

		for (int end = n / 3; i < end; ++i) {
			onHand.add(cards[i]);
		}

		int round = 1;
		for (; i < n; ++round) {
			discarded.add(cards[i++]);
			discarded.add(cards[i++]);

			if (test(onHand, onHand, target))
				continue;
			else if (coin >= 1 && test(onHand, discarded, target))
				--coin;
			else if (coin >= 2 && test(discarded, discarded, target))
				coin -= 2;
			else
				break;
		}
		return round;
	}

	private boolean test(Set<Integer> set1, Set<Integer> set2, int target) {
		for (int v: set1) {
			if (set2.contains(target - v)) {
				set1.remove(v);
				set2.remove(target - v);
				return true;
			}
		}
		return false;
	}
}