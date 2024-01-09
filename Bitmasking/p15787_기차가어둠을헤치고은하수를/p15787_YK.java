package Bitmasking.p15787_기차가어둠을헤치고은하수를;

import java.io.*;
import java.util.*;
public class p15787_YK {
    static boolean[] visited = new boolean[1 << 20];
    static int[] trains;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        trains = new int[N];

        for (int i = 0, a, b, c = -1; i < M; ++i) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken()) - 1;
            if (a < 3) {
                c = Integer.parseInt(st.nextToken()) - 1;
            }

            switch (a) {
                case 1:
                    getOn(b, c);
                    break;
                case 2:
                    getOff(b, c);
                    break;
                case 3:
                    moveBack(b);
                    break;
                case 4:
                    moveFront(b);
                    break;
            }
        }

        int result = 0;
        for (int i = 0; i < N; ++i) {
            if (visited[trains[i]]) continue;
            ++result;
            visited[trains[i]] = true;
        }

        System.out.println(result);
        br.close();
    }

    private static void getOn(int i, int x) {
        trains[i] |= (1 << x);
    }

    private static void getOff(int i, int x) {
        int k = 1 << x;
        if ((trains[i] & k) == 0) return;
        trains[i] -= k;
    }

    private static void moveBack(int i) {
        trains[i] <<= 1;
        if (trains[i] >= (1 << 20)) {
            trains[i] -= (1 << 20);
        }
    }

    private static void moveFront(int i) {
        trains[i] >>= 1;
    }
}
