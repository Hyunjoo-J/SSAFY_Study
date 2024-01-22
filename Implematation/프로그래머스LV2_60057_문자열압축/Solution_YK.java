package Implematation.프로그래머스LV2_60057_문자열압축;

public class Solution_YK {
    class Solution {
        public static int solution(String s) {
            int len = s.length();
            int answer = len;
            char[] string = new char[len];

            int k = 0; // 단위 문자열 길이
            while (++k <= len / 2) {
                int tmp = 0; // 현재 단위에서 압축 시 문자열 길이
                int p = 0;
                while (p < len) {
                    int limit = p + k;
                    if (limit >= len) { // 남은 문자열이 더 짧으면
                        tmp += len - p;
                        break;
                    }

                    int i = 0;
                    while (p < limit) {
                        string[i++] = s.charAt(p);
                        ++tmp;
                        ++p;
                    }

                    boolean flag = false;
                    int count = 1;
                    while (!flag) {
                        for (int j = 0; j < k; ++j) {
                            if (p >= len || string[j] != s.charAt(p)) {
                                p -= j;
                                flag = true;
                                break;
                            }
                            ++p;
                        }
                        if (!flag) {
                            if (count == 1) tmp += 1;
                            if (count == 9) tmp += 1;
                            ++count;
                        }
                    }
                }

                answer = Math.min(answer, tmp);
            }

            return answer;
        }
    }
}
