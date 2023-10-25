package DynamicProgramming.p2293_동전1;

import java.util.*;
import java.io.*;

public class p2293_MJ {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static  int n, k, dp[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// 동전의 합이 N이 되는 경우의 수 : dp -> dp[10] : 동전의 합이 10이 되는 경우의 수
		dp = new int[k+1];
		dp[0] = 1;

		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			for(int j=num; j<k+1; j++) {
				dp[j] += dp[j-num];
			}
		}
		
		System.out.println(dp[k]);
		
		br.close();
	}
}
