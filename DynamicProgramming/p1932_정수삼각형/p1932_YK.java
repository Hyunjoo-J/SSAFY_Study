package DynamicProgramming.p1932_정수삼각형;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1932_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int s = 0;
        for (int i = 1; i <= N; ++i) {
            s += i;
        }
        int[] tree = new int[s];
        int[] memo = new int[s];

        int cnt = 0;
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = i; j > 0; --j) {
                tree[cnt++] = Integer.parseInt(st.nextToken());
            }
        }

        memo[0] = tree[0];
        long result = memo[0];
        cnt = 1;
        for (int i = 1; i < N; ++i) {
            memo[cnt] = tree[cnt] + memo[cnt - i];
            result = Math.max(result, memo[cnt]);
            cnt++;

            for (int j = i - 1; j > 0; --j) {
                memo[cnt] = tree[cnt] + Math.max(memo[cnt - i - 1], memo[cnt - i]);
                result = Math.max(result, memo[cnt]);
                cnt++;
            }

            memo[cnt] = tree[cnt] + memo[cnt - i - 1];
            result = Math.max(result, memo[cnt]);
            cnt++;
        }

        System.out.println(result);
    }
}