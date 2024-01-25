package String.프로그래머스LV1_72410_신규아이디추천;

class Solution_HC {
    public String solution(String new_id) {
        String id = new_id;
        StringBuilder sb = new StringBuilder();

        // 1
        id = id.toLowerCase();

        // 2
        for (char c: id.toCharArray()) {
            if (Character.isDigit(c) || ('a' <= c && c <= 'z') || c == '-' || c == '_' || c == '.')
                sb.append(c);
        }
        id = sb.toString();

        // 3
        sb.setLength(0);
        for (char c: id.toCharArray()) {
            if (c == '.' && sb.length() > 0 && sb.charAt(sb.length() - 1) == '.')
                continue;
            sb.append(c);
        }
        id = sb.toString();

        // 4
        if (id.length() > 0 && id.charAt(0) == '.')
            id = id.substring(1);
        if (id.length() > 0 && id.charAt(id.length() - 1) == '.')
            id = id.substring(0, id.length() - 1);

        // 5
        if (id.length() == 0)
            id = "a";

        // 6
        sb = new StringBuilder(id);
        sb.setLength(Math.min(sb.length(), 15));
        while (sb.charAt(sb.length() - 1) == '.')
            sb.setLength(sb.length() - 1);

        // 7
        while (sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }
        id = sb.toString();

        return id;
    }
}