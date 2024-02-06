package DataStructure.프로그래머스LV2_42888_오픈채팅방;

import java.io.*;
import java.util.*;

public class Solution_YK {
    public String[] solution(String[] record) {
        StringTokenizer st;
        int el = 0;
        Map<String, String> map = new HashMap<>();

        String word1, word2, word3;
        for (String r : record) {
            st =  new StringTokenizer(r);
            word1 = st.nextToken();
            word2 = st.nextToken();
            if (!word1.equals("Leave")) {
                word3 = st.nextToken();
                map.put(word2, word3);
            }

            if (!word1.equals("Change")) {
                ++el;
            }
        }

        String[] answer = new String[el];
        int i = 0;
        for (String r : record) {
            st =  new StringTokenizer(r);
            word1 = st.nextToken();
            word2 = st.nextToken();

            if (word1.equals("Enter")) {
                answer[i++] = map.get(word2) + "님이 들어왔습니다.";
            } else if (word1.equals("Leave")) {
                answer[i++] = map.get(word2) + "님이 나갔습니다.";
            }
        }

        return answer;
    }
}
