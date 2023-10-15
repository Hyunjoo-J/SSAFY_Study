package BinarySearch.p2512_예산;

import java.io.*;
import java.util.*;
public class p2512_YK {

    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int result = 0;
        for (int i = 0; i < N; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
        }
        M = Integer.parseInt(br.readLine());
        Arrays.sort(nums);
        if (sum <= M) result = nums[N - 1];

        if (result == 0) result = upper_bound();
        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int upper_bound() {
        int left = 1;
        int right = nums[N - 1];

        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(mid)) { // 작거나 같다
                left = mid + 1;
            } else { // 크다
                right = mid - 1;
            }
        }

        return right;
    }

    private static boolean check(int mid) {
        int tmp = M;
        for (int n : nums) {
            tmp -= Math.min(n, mid);
        }
        return tmp >= 0;
    }
}
