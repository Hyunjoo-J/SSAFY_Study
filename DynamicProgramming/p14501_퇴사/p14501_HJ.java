package DynamicProgramming.p14501_퇴사;

import java.util.*;
import java.io.*;
public class p14501_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] schedule = new int[N + 2][2];
		for(int i = 1; i <= N; ++i){
			st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		//해당 날짜에 얻을 수 있는 최대 금액
		//일이 끝난 날에 금액이 들어감
		int[] dp = new int[N + 2];
		int max = -1;
		//i번째 날짜의 일을 한다면?
		for(int i = 1; i < N + 2; ++i){
			if(max < dp[i]){
				max = dp[i];
			}
			int next = i + schedule[i][0];
			if(next < N + 2){
				dp[next] = Math.max(max + schedule[i][1], dp[next]);
			}
		}
		System.out.println(dp[N + 1]);
	}
}