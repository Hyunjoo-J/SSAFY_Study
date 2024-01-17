package Greedy.프로그래머스LV2_42883_큰수만들기;

import java.util.*;

class Solution_HC {
    public String solution(String number, int k) {
        int n = number.length();
        int[] num = new int[n];
        for (int i = 0; i < n; ++i) {
            num[i] = number.charAt(i) - '0';
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int x: num) {
            while (k > 0 && !stack.isEmpty() && stack.peek() < x) {
                stack.pop();
                --k;
            }
            stack.push(x);
        }

        // *******
        while (k-- > 0) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}