package Greedy.p2885_초콜릿식사;

import java.io.*;

public class p2885_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());

        int choco = 1;
        while (choco < k) choco <<= 1;

        int cnt = 0;
        sb.append(choco).append(" ");
        if (choco == k) {
            k = 0;
        }

        while (k > 0) {
            choco >>= 1;
            ++cnt;
            while (k - choco >= 0) {
                k -= choco;
            }
        }

        sb.append(cnt);
        System.out.println(sb);
        br.close();
    }
}
