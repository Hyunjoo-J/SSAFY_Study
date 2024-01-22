package Implematation.프로그래머스LV1_258712_가장많이받은선물;

import java.util.*;

public class Solution_YK {
    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;
        StringTokenizer st;

        HashMap<String, Integer> hMap = new HashMap<>();
        int size = friends.length;
        int[] giftScore = new int[size];
        int[][] giftRecord = new int[size][size];

        for (int i = 0; i < size; ++i) {
            hMap.put(friends[i], i);
        }

        for (String gift : gifts) {
            st = new StringTokenizer(gift);
            String from = st.nextToken();
            String to = st.nextToken();
            giftScore[hMap.get(from)]++;
            giftScore[hMap.get(to)]--;
            giftRecord[hMap.get(from)][hMap.get(to)]++;
        }

        for (int i = 0; i < size; ++i) {
            int tmp = 0;
            for (int j = 0; j < size; ++j) {
                if (giftRecord[i][j] > giftRecord[j][i]) ++tmp;
                else if (giftRecord[i][j] == giftRecord[j][i]
                        && giftScore[i] > giftScore[j]) ++tmp;
            }
            answer = Math.max(tmp, answer);
        }

        return answer;
    }
}
