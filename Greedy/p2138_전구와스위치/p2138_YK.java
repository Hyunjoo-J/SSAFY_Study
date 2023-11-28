package Greedy.p2138_전구와스위치;

import java.io.*;
import java.util.*;

public class p2138_YK {
    static final int INF = Integer.MAX_VALUE >> 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] lights = new boolean[N];
        boolean[] result = new boolean[N];

        char[] line = br.readLine().toCharArray();
        for (int i = 0; i < N; ++i) {
            result[i] = line[i] == '1';
        }
        line = br.readLine().toCharArray();
        for (int i = 0; i < N; ++i) {
            lights[i] = line[i] == '1';
        }

        int answer = INF;

        // 첫 번째 전구를 켰을 때
        boolean[] tmp = Arrays.copyOf(lights, N);
        tmp[0] = !tmp[0];
        tmp[1] = !tmp[1];
        answer = Math.min(answer, play(tmp, result) + 1);

        // 첫 번째 전구를 켜지 않았을 때
        answer = Math.min(answer, play(Arrays.copyOf(lights, N), result));

        System.out.println(answer == INF ? -1 : answer);
        br.close();
    }

    private static int play(boolean[] tmp, boolean[] result) {
        int cnt = 0;
        for (int i = 0, size = tmp.length - 1; i < size; ++i) {
            if (tmp[i] != result[i]) {
                ++cnt;
                tmp[i] = !tmp[i];
                tmp[i + 1] = !tmp[i + 1];
                if (i + 2 <= size) tmp[i + 2] = !tmp[i + 2];
            }
        }

        for (int i = 0, size = tmp.length; i < size; ++i) {
            if (tmp[i] != result[i]) return INF;
        }

        return cnt;
    }
}
