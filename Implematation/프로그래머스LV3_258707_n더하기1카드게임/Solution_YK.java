package Implematation.프로그래머스LV3_258707_n더하기1카드게임;

import java.util.*;
public class Solution_YK {
    public int solution(int coin, int[] cards) {
        int N = cards.length;
        int N3 = N / 3;
        int num = N + 1;

        List<Integer> myCards = new ArrayList<>();
        List<Integer> saveCards = new ArrayList<>();
        boolean[] hasCard = new boolean[N + 1];
        boolean[] saveCard = new boolean[N + 1];

        for (int i = 0; i < N3; ++i) {
            hasCard[cards[i]] = true;
            myCards.add(cards[i]);
        }

        int round = 0;
        boolean flag;
        for (int i = N3; i < N; i += 2) {
            ++round;
            int c1 = cards[i], c2 = cards[i + 1];
            saveCard[c1] = true;
            saveCard[c2] = true;
            saveCards.add(c1);
            saveCards.add(c2);

            // 가진 카드 중에 짝꿍이 있는지
            flag = false;
            for (int card : myCards) {
                if (hasCard[num - card]) {
                    myCards.remove(Integer.valueOf(card));
                    myCards.remove(Integer.valueOf(num - card));
                    flag = true;
                    break;
                }
            }

            if (flag) continue;
            if (coin == 0) return round;

            // 저장된 카드에 현재 가진 카드와 짝꿍이 있는지
            for (int card : myCards) {
                if (saveCard[num - card]) {
                    myCards.remove(Integer.valueOf(card));
                    saveCards.remove(Integer.valueOf(num - card));
                    --coin;
                    flag = true;
                    break;
                }
            }

            if (flag) continue;
            if (coin < 2) return round;

            // 저장된 카드 사이에 짝꿍이 있는지 검사
            for (int card : saveCards) {
                if (saveCard[num - card]) {
                    saveCards.remove(Integer.valueOf(card));
                    saveCards.remove(Integer.valueOf(num - card));
                    coin -= 2;
                    flag = true;
                    break;
                }
            }

            if (!flag) return round;
        }

        return round + 1;
    }
}
