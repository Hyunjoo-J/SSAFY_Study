package Implematation.p17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p17136_YK {

    static boolean[][] map = new boolean[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; ++j) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        dfs(0, 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        br.close();
    }

    private static void dfs(int x, int y, int cnt) {

        if (cnt > result) return;

        // 종료 조건 (모두 채워졌거나, 끝까지 탐색했거나)
        // y를 늘려가므로 x = 9 / y = 10 일 때 종료
        if (check()) {
            result = Math.min(result, cnt);
            return;
        }

        if (x == 9 && y == 10) return;

        if (y > 9) {
            dfs(x + 1, 0, cnt);
            return;
        }

        // 붙일 수 있다면 붙여보기
        if (map[x][y]) {
            // 큰 거부터 붙여보기
            for (int size = 5; size > 0; --size) {
                // 종이가 없다면 pass
                if (paper[size] == 0) continue;

                // 종이가 범위 밖이라면
                if (x + size > 10 || y + size > 10) continue;

                // 종이 붙일 수 있나?
                boolean flag = true;
                for (int i = x; i < x + size; ++i) {
                    for (int j = y; j < y + size; ++j) {
                        if (!map[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                }

                // 붙일 수 없다!
                if (!flag) continue;


                // 붙일 수 있다!
                for (int i = x; i < x + size; ++i) {
                    for (int j = y; j < y + size; ++j) {
                        map[i][j] = false;
                    }
                }

                --paper[size];
                dfs(x, y + 1, cnt + 1);
                ++paper[size];

                // 다시 떼기
                for (int i = x; i < x + size; ++i) {
                    for (int j = y; j < y + size; ++j) {
                        map[i][j] = true;
                    }
                }
            }
        }
        else { // 붙이지 않기
            dfs(x, y + 1, cnt);
        }

    }

    private static boolean check() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (map[i][j]) return false;
            }
        }
        return true;
    }
}
