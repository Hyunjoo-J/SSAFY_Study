package BackTracking.p15661_링크와스타트;

import java.io.*;
import java.util.*;
public class p15661_YK {

    static int N, result = Integer.MAX_VALUE;
    static int[][] list;
    static boolean[] isSelected;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new int[N][N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isSelected[0] = true;
        subset(1);

        System.out.println(result);
        br.close();
    }

    private static void subset(int cnt) {
        if (result == 0) return;

        if (cnt == N) {
            int teamA = 0, teamB = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (isSelected[i] && isSelected[j])
                        teamA += list[i][j];
                    else if (!isSelected[i] && !isSelected[j])
                        teamB += list[i][j];
                }
            }
            result = Math.min(result, Math.abs(teamA - teamB));
            return;
        }

        isSelected[cnt] = true;
        subset(cnt + 1);
        isSelected[cnt] = false;
        subset(cnt + 1);
    }
}
