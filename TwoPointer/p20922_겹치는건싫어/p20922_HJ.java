package TwoPointer.p20922_겹치는건싫어;

import java.util.*;
import java.io.*;
public class p20922_HJ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] cnt = new int[100001];
        int start = 0, end = 0;
        int ans = 0;
        while (end < N) {
            while(end < N && cnt[arr[end]] + 1 <= K){
                ++cnt[arr[end]];
                ++end;
            }
            int len = end - start;
            ans = Math.max(ans, len);
            --cnt[arr[start]];
            ++start;
        }
        System.out.println(ans);
    }
}
