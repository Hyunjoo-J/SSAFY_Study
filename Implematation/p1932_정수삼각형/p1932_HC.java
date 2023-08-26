package Implematation.p1932_정수삼각형;

import java.io.*;
import java.util.*;

public class p1932_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] tri = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; ++j) {
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                tri[i][j] += Math.max(tri[i - 1][j - 1], tri[i - 1][j]);
            }
        }

        int answer = 0;
        for (int j = 1; j <= n; ++j) {
            answer = Math.max(answer, tri[n][j]);
        }
        System.out.println(answer);
        br.close();
    }
}