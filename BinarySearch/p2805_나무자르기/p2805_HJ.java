package BinarySearch.p2805_나무자르기;
import java.util.*;
import java.io.*;
public class p2805_HJ {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] height = new int[N];
        for (int i = 0; i < N; ++i) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        long left = 0;
        long right = 2000000000;
        long mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if(cut(height, mid) >= M)
                left = mid + 1;
            else
                right = mid - 1;
        }
        System.out.println(left - 1);
    }

    private static int cut(int[] height, long mid) {
        long res = 0;
        for(int a : height) {
            if(a - mid > 0)
                res += a - mid;
        }
        if(res > Integer.MAX_VALUE)
            res = Integer.MAX_VALUE;
        return (int)res;
    }

}