package BinarySearch.p2110_공유기설치;

import java.io.*;
import java.util.*;

public class p2110_YK {

    static int[] houses;
    static int N, C;
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for (int i = 0; i < N; ++i) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        int start = 1;
        int end = houses[N - 1] - houses[0];
        if (C == 2) {
            System.out.println(end);
            return;
        }
        binarySearch(start, end, C - 1);

        System.out.println(result);
        br.close();
    }

    private static void binarySearch(int start, int end, int c) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        int ans = check(mid);
        if (ans >= c) {
            result = mid;
            binarySearch(mid + 1, end, c);
        } else {
            binarySearch(start, mid, c);
        }
    }

    private static int check(int dist) {
        int tmp = 0, ans = 0;

        for (int i = 1; i < N; ++i) {
            if (tmp + (houses[i] - houses[i - 1]) >= dist) {
                ++ans;
                tmp = 0;
            } else {
                tmp += (houses[i] - houses[i - 1]);
            }
        }

        return ans;
    }
}
