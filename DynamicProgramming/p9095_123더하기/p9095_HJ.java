package DynamicProgramming.p9095_123더하기;

import java.io.*;
public class p9095_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int max = 0;
		for(int i = 0; i < N; ++i){
			num[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, num[i]);
		}
		int[] dp;
		if(max < 3) {
			dp = new int[3];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
		}else{
			dp = new int[max + 1];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for(int i = 4; i <= max; ++i){
				dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; ++i){
			sb.append(dp[num[i]]).append("\n");
		}
		System.out.print(sb);
	}
}
