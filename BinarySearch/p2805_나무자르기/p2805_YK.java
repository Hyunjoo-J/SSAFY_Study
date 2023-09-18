package BinarySearch.p2805_나무자르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p2805_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        int start = 0, end = 0, mid;
        for (int i = 0; i < N; ++i) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (end < trees[i]) end = trees[i];
        }

        long tmp;
        while (start <= end) {
            mid = (start + end) / 2;
            tmp = cut(trees, N, mid);
            if (tmp < M) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(end);
        br.close();
    }

    private static long cut(int[] trees, int n, int mid) {
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (trees[i] <= mid) continue;
            ans += trees[i] - mid;
        }
        return ans;
    }

}