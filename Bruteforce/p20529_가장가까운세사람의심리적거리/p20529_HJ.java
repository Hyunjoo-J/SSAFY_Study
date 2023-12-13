package Bruteforce.p20529_가장가까운세사람의심리적거리;

import java.util.*;
import java.io.*;
public class p20529_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0){
			int min = 12;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			//33개 이상이면 입력 값과 상관없이 결과는 무조건 0 (비둘기집 원리)
			if(N > 32){
				sb.append(0).append("\n");
				continue;
			}
			String[] input = new String[N];
			for(int i = 0; i < N; ++i){
				input[i] = st.nextToken();
			}

			next:for(int i = 0; i < N; ++i){
				for(int j = i + 1; j < N; ++j){
					for(int k = j + 1; k < N; ++k){
						min = Math.min(min, getDiff(input[i], input[j], input[k]));
						if(min == 0)
							break next;
					}
				}
			}
			sb.append(min).append("\n");
		}
		System.out.print(sb);
	}

	private static int getDiff(String a, String b, String c){
		int dist = 0;
		for(int i = 0; i < 4; ++i){
			dist += a.charAt(i) == b.charAt(i) ? 0 : 1;
			dist += a.charAt(i) == c.charAt(i) ? 0 : 1;
			dist += b.charAt(i) == c.charAt(i) ? 0 : 1;
		}
		return dist;
	}
}
