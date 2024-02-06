package String.프로그래머스LV1_72410_신규아이디추천;

public class Solution_YK {
    public String solution(String new_id) {
        StringBuilder tmp = new StringBuilder();

        // 1, 2단계 일괄처리
        for (int i = 0, size = new_id.length(); i < size; ++i) {
            char c = new_id.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                tmp.append((char) ('a' + c - 'A'));
            } else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
                    || c == '-' || c == '_' || c == '.') {
                tmp.append(c);
            }
        }

        // 3단계
        new_id = tmp.toString();
        tmp = new StringBuilder();

        for (int i = 0, size = new_id.length(); i < size; ++i) {
            char c = new_id.charAt(i);
            if (c == '.') {
                tmp.append('.');
                while (i < size && c == '.') {
                    c = new_id.charAt(i++);
                }
                if (c != '.') {
                    tmp.append(c);
                    --i;
                }
                continue;
            }
            tmp.append(c);
        }

        // 4단계
        new_id = tmp.toString();
        tmp = new StringBuilder();

        for (int i = 0, size = new_id.length(); i < size; ++i) {
            char c = new_id.charAt(i);
            if (i == 0 && c == '.') continue;
            if (i == size - 1 && c == '.') continue;
            tmp.append(c);
        }

        // 5, 6, 7단계
        new_id = tmp.toString();
        tmp = new StringBuilder();

        if (new_id.length() == 0) tmp.append("aaa");
        else if (new_id.length() > 15) {
            for (int i = 0; i < 14; ++i) {
                tmp.append(new_id.charAt(i));
            }
            if (new_id.charAt(14) != '.') {
                tmp.append(new_id.charAt(14));
            }
        } else if (new_id.length() < 3) {
            tmp.append(new_id);
            while (tmp.length() < 3) {
                tmp.append(new_id.charAt(new_id.length() - 1));
            }
        } else {
            tmp.append(new_id);
        }

        return tmp.toString();
    }
}
