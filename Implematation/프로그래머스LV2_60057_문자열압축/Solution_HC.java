package Programmers.LV2_60057_문자열압축;

class Solution_HC {
    public int solution(String s) {
        if (s.length() <= 1) {
            return 1;
        }

        int n = s.length();
        int answer = 123456789;
        StringBuilder sb = new StringBuilder();

        for (int gap = 1, end = (n >> 1) + 1; gap < end; ++gap) {
            String[] splited = strSplit(s, gap);
            for (int i = 0, length = splited.length; i < length; ++i) {
                String first = splited[i];
                int j = i + 1;
                int cnt = 1;
                for (; j < length; ++j, ++cnt) {
                    if (!first.equals(splited[j])) {
                        break;
                    }
                }

                i = j - 1;
                if (cnt > 1)
                    sb.append(cnt);
                sb.append(first);
            }
            answer = Math.min(answer, sb.length());
            sb.setLength(0);    // clear
        }
        return answer;
    }

    private String[] strSplit(String s, int gap) {
        int n = s.length();
        String[] res = new String[n / gap + (n % gap > 0 ? 1 : 0)];
        for (int i = 0, idx = 0; i < n; i += gap, ++idx) {
            res[idx] = s.substring(i, Math.min(i + gap, n));
        }
        return res;
    }
}