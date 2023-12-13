package Greedy.p11501_주식;

import java.util.*;
import java.io.*;
public class p11501_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			int Day = Integer.parseInt(br.readLine());
			long ans = 0;
			st = new StringTokenizer(br.readLine());
			int[] stock = new int[Day];
			for(int i = 0; i < Day; ++i){
				stock[i] = Integer.parseInt(st.nextToken());
			}
			int max = stock[Day - 1];
			for(int i = Day - 2; i >= 0; --i){
				if(stock[i] <= max)
					ans += max - stock[i];
				else
					max = stock[i];
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
}
