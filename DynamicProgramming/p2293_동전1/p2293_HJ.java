package DynamicProgramming.p2293_동전1;

import java.io.*;
import java.util.StringTokenizer;

public class p2293_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] money = new int[N + 1];
		int[] dp = new int[K + 1];
		dp[0] = 1;
		for(int i = 1; i <= N; ++i){
			money[i] = Integer.parseInt(br.readLine());
			for(int j = money[i]; j <= K; ++j){
				dp[j] += dp[j - money[i]];
			}
		}
		System.out.println(dp[K]);
	}
}
