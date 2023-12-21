package Bruteforce.p27172_수나누기게임;
import java.util.*;
import java.io.*;
public class p27172_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		int[] score = new int[1000001];
		boolean[] num = new boolean[1000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i){
			card[i] = Integer.parseInt(st.nextToken());
			num[card[i]] = true;
		}

		for(int i = 0; i < N; ++i){
			for(int j = card[i] << 1; j < 1000001; j += card[i]){
				if(num[j]){
					--score[j];
					++score[card[i]];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; ++i){
			sb.append(score[card[i]]).append(" ");
		}
		System.out.print(sb);
	}
}
