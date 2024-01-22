package Greedy.프로그래머스LV2_42883_큰수만들기;
public class Solution_YK {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int N = number.length();
        boolean[] selected = new boolean[N];

        int left = 0;

        k = N - k;
        while (k > 0) {
            if (left + k >= N) {
                for (int i = left; i < N; ++i) {
                    selected[i] = true;
                }
                break;
            }

            int max = 0;
            for (int i = left, size = N - k; i <= size; ++i) {
                int now = number.charAt(i) - '0';
                if (max < now) {
                    max = now;
                    left = i;
                }
            }

            selected[left] = true;
            --k;
            ++left;
        }

        boolean zeroStart = true;
        for (int i = 0; i < N; ++i) {
            if (selected[i]) {
                if (zeroStart && number.charAt(i) == '0') continue;
                zeroStart = false;
                answer.append(number.charAt(i));
            }
        }

        return answer.toString().equals("") ? "0" : answer.toString();
    }
}
