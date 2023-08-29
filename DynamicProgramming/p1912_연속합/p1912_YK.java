package DynamicProgramming.p1912_연속합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1912_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] memo = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        memo[N - 1] = input[N - 1];
        int result = input[N - 1];
        for (int i = N - 2; i >= 0; --i) {
            // memo[i] 는 input[i], input[i] + memo[i + 1] 중에 큰거
            memo[i] = Math.max(memo[i + 1] + input[i], input[i]);
            result = Math.max(memo[i], result);
        }

        System.out.println(result);
    }
}