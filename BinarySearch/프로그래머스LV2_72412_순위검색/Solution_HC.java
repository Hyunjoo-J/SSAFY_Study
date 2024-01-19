package BinarySearch.프로그래머스LV2_72412_순위검색;

import java.util.*;

class Solution_HC {

    public int[] solution(String[] infos, String[] queries) {
        Map<String, Integer> map = getConditionConvertMap();
        List<Integer>[] cache = new ArrayList[1 << 8];
        for (int i = 0, end = 1 << 8; i < end; ++i) {
            cache[i] = new ArrayList<>();
        }

        // caching
        StringTokenizer st;
        for (String info: infos) {
            st = new StringTokenizer(info);
            int lang = map.get(st.nextToken());
            int job = map.get(st.nextToken());
            int career = map.get(st.nextToken());
            int food = map.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            for (int a = 0; a <= lang; a += lang) {
                for (int b = 0; b <= job; b += job) {
                    for (int c = 0; c <= career; c += career) {
                        for (int d = 0; d <= food; d += food) {
                            int status = a | b | c | d;
                            cache[status].add(score);
                        }
                    }
                }
            }
        }

        for (int i = 0, end = 1 << 8; i < end; ++i) {
            cache[i].sort((o1, o2) -> Integer.compare(o1, o2));
        }

        // query
        int idx = 0;
        int[] answer = new int[queries.length];
        for (String query: queries) {
            st = new StringTokenizer(query.replace(" and", ""));
            int lang = map.get(st.nextToken());
            int job = map.get(st.nextToken());
            int career = map.get(st.nextToken());
            int food = map.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            int status = lang | job | career | food;
            answer[idx++] = cache[status].size() - lowerBound(cache[status], 0, cache[status].size() - 1, score);
        }
        return answer;
    }

    private static int lowerBound(List<Integer> list, int left, int right, int k) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (list.get(mid) >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }

    private static Map<String, Integer> getConditionConvertMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("-", 0);
        map.put("cpp", 1);
        map.put("java", 1 << 1);
        map.put("python", (1 << 1) + 1);
        map.put("frontend", 1 << 2);
        map.put("backend", 1 << 3);
        map.put("junior", 1 << 4);
        map.put("senior", 1 << 5);
        map.put("chicken", 1 << 6);
        map.put("pizza", 1 << 7);
        return map;
    }
}