package PrefixSum.p14476_최대공약수하나빼기;

import java.io.*;
import java.util.*;

public class p14476_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] lgcd = new int[N];
        int[] rgcd = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        lgcd[0] = nums[0]; rgcd[N - 1] = nums[N - 1];
        for (int i = 1; i < N; ++i) {
            lgcd[i] = gcd(lgcd[i - 1], nums[i]);
            rgcd[N - i - 1] = gcd(rgcd[N - i], nums[N - i - 1]);
        }

        int gcd = rgcd[0];
        int num = -1;

        if (rgcd[1] > gcd) {
            gcd = rgcd[1];
            num = nums[0];
        }

        if (lgcd[N - 2] > gcd) {
            gcd = lgcd[N - 2];
            num = nums[N - 1];
        }

        for (int i = 1; i < N - 1; ++i) {
            int tmp = gcd(lgcd[i - 1], rgcd[i + 1]);
            if (tmp > gcd) {
                gcd = tmp;
                num = nums[i];
            }
        }

        if (num == -1) bw.write(num + " ");
        else {
            bw.write(gcd + " " + num);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}