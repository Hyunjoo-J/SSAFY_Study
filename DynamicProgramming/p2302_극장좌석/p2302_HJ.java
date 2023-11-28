package DynamicProgramming.p2302_극장좌석;
import java.io.*;
public class p2302_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= N; ++i){
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int ans = 1;
		int idx = 0;
		for(int i = 0; i < M; ++i){
			int vip = Integer.parseInt(br.readLine());
			ans *= dp[vip - idx - 1];
			idx = vip;
		}
		ans *= dp[N - idx];
		System.out.println(ans);
	}
}