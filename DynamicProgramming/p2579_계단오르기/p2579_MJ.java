package DynamicProgramming.p2579_계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2579_MJ {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		int N = Integer.parseInt(br.readLine());
		
		int stair[] = new int[N+1];
		int score[] = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		score[1] = stair[1];
		
		if(N>1)
			score[2] = stair[1] + stair[2];
		if(N>2)
			score[3] = Math.max(stair[1]+stair[3], stair[2]+stair[3]);
		
		for(int i=4; i<=N; i++) {
			score[i] = Math.max(score[i-3]+stair[i-1], score[i-2]) + stair[i];
		}
		
		System.out.println(score[N]);
		
		br.close();
	}
}
