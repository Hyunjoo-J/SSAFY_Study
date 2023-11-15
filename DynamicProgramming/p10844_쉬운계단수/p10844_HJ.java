package DynamicProgramming.p10844_쉬운계단수;
import java.io.*;
public class p10844_HJ {
	private static final int MOD = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[10];
		int[] cal = new int[10];
		for(int i = 1; i < 10; ++i)
			dp[i] = 1;

		for(int i = 2; i <= N; ++i){
			cal[0] = dp[1];
			cal[9] = dp[8];
			for(int j = 1; j < 9; ++j)
				cal[j] = (dp[j - 1] + dp[j + 1])  % MOD;
			int[] copy = dp;
			dp = cal;
			cal = copy;
		}

		int ans = 0;
		for(int a : dp)
			ans = (a + ans) % MOD;
		System.out.println(ans);
	}
}
