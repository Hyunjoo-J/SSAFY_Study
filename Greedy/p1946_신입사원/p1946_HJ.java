package Greedy.p1946_신입사원;

import java.util.*;
import java.io.*;
public class p1946_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			int N = Integer.parseInt(br.readLine());
			int[] score = new int[N];

			for(int i = 0; i < N; ++i){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				score[a - 1] = b;
			}
			int ans = 1;
			int min = score[0]; //서류 1등의 면접 순위
			for(int i = 1; i < N; ++i){
				if(min > score[i]){
					min = score[i];
					++ans;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
}
