package BFS_DFS.프로그래머스LV3_77486_다단계칫솔판매;

import java.util.*;

public class Solution_YK {

    static int N;
    static Map<String, Integer> money = new HashMap<>();
    static Map<String, String> parents = new HashMap<>();
    static final String CENTER = "-";

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        N = enroll.length;
        int[] answer = new int[N];

        money.put(CENTER, 0);
        for (String e : enroll) {
            money.put(e, 0);
        }

        for (int i = 0; i < N; ++i) {
            parents.put(enroll[i], referral[i]);
        }

        for (int i = 0, size = seller.length; i < size; ++i) {
            dfs(seller[i], amount[i] * 100);
        }

        for (int i = 0; i < N; ++i) {
            answer[i] = money.get(enroll[i]);
        }
        return answer;
    }

    private void dfs(String s, int m) {
        int pay = m / 10;
        money.put(s, money.get(s) + m - pay);

        if (pay == 0) return;

        String parent = parents.get(s);
        if (parent.equals(CENTER)) return;

        dfs(parent, pay);
    }
}
