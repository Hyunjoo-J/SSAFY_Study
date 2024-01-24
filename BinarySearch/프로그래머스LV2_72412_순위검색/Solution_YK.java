package BinarySearch.프로그래머스LV2_72412_순위검색;

import java.util.*;

public class Solution_YK {
    static Map<String, Integer> hMap = new HashMap<>();
    static List<Integer>[][][][] list;
    static int[] q;
    public static int[] solution(String[] info, String[] query) {
        init();

        int N = info.length;
        int M = query.length;

        int[] answer = new int[M];
        StringTokenizer st;

        for (int i = 0, a, b, c, d, score; i < N; ++i) {
            st = new StringTokenizer(info[i]);
            a = hMap.get(st.nextToken());
            b = hMap.get(st.nextToken());
            c = hMap.get(st.nextToken());
            d = hMap.get(st.nextToken());
            score = Integer.parseInt(st.nextToken());
            updateList(a, b, c, d, score);
        }

        sort();

        for (int i = 0; i < M; ++i) {
            String s = query[i];
            st = new StringTokenizer(s);
            q = new int[5];
            for (int j = 0, cnt = 0; j < 7; ++j) {
                String line = st.nextToken();
                if (line.equals("and")) continue;
                q[cnt++] = hMap.get(line);
            }
            q[4] = Integer.parseInt(st.nextToken());
            if (list[q[0]][q[1]][q[2]][q[3]].isEmpty()) {
                answer[i] = 0;
                continue;
            }

            int idx = binarySearch();
            answer[i] = list[q[0]][q[1]][q[2]][q[3]].size() - idx;
        }

        return answer;
    }

    private static int binarySearch() {
        int left = 0, right = list[q[0]][q[1]][q[2]][q[3]].size();
        while (left <= right) {
            int mid = (left + right) / 2;

            if (list[q[0]][q[1]][q[2]][q[3]].get(mid) >= q[4]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right + 1;
    }

    private static void updateList(int a, int b, int c, int d, int score) {
        list[0][0][0][0].add(score);
        list[a][0][0][0].add(score);
        list[0][b][0][0].add(score);
        list[0][0][c][0].add(score);
        list[0][0][0][d].add(score);
        list[a][b][0][0].add(score);
        list[a][0][c][0].add(score);
        list[a][0][0][d].add(score);
        list[0][b][c][0].add(score);
        list[0][b][0][d].add(score);
        list[0][0][c][d].add(score);
        list[a][b][c][0].add(score);
        list[a][b][0][d].add(score);
        list[a][0][c][d].add(score);
        list[0][b][c][d].add(score);
        list[a][b][c][d].add(score);
    }

    private static void sort() {
        for (int a = 0; a < 4; ++a) {
            for (int b = 0; b < 3; ++b) {
                for (int c = 0; c < 3; ++c) {
                    for (int d = 0; d < 3; ++d) {
                        Collections.sort(list[a][b][c][d]);
                    }
                }
            }
        }
    }

    private static void init() {
        hMap.put("-", 0);
        hMap.put("cpp", 1);
        hMap.put("java", 2);
        hMap.put("python", 3);
        hMap.put("backend", 1);
        hMap.put("frontend", 2);
        hMap.put("junior", 1);
        hMap.put("senior", 2);
        hMap.put("chicken", 1);
        hMap.put("pizza", 2);

        list = new ArrayList[4][3][3][3];
        for (int a = 0; a < 4; ++a) {
            for (int b = 0; b < 3; ++b) {
                for (int c = 0; c < 3; ++c) {
                    for (int d = 0; d < 3; ++d) {
                        list[a][b][c][d] = new ArrayList<>();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] s1 = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] s2 = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(solution(s1, s2)));
    }
}
