package Bruteforce.p18111_마인크래프트;

import java.io.*;
import java.util.*;

public class p18111_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] land = new int[N * M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                land[i * M + j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(land);

        int result = land[N * M - 1] + 1;
        int height = land[N * M - 1] + 1;
        int time = Integer.MAX_VALUE;

        while (height >= 0) {
            int blocks = B;
            int tmpTime = 0;

            for (int i = N * M - 1, diff; i >= 0; --i) {
                diff = Math.abs(land[i] - height);

                if (land[i] > height) { // 인벤토리에 넣기
                    blocks += diff;
                    tmpTime += diff << 1;
                } else if (land[i] < height) { // 놓기
                    blocks -= diff;
                    tmpTime += diff;
                }
            }

            if (blocks >= 0 && time > tmpTime) {
                time = tmpTime;
                result = height;
            }

            --height;
        }

        System.out.println(time + " " + result);
        br.close();
    }
}
