package Greedy.p2885_초콜릿식사;

import java.io.*;
public class p2885_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = 1;
		int cnt = 0;
		while(N < K)
			N <<= 1;
		StringBuilder sb = new StringBuilder();
		sb.append(N).append(" ");
		while(K > 0){
			if(K >= N)
				K -= N;
			else{
				N >>= 1;
				++cnt;
			}
		}
		sb.append(cnt);
		System.out.println(sb);
	}
}