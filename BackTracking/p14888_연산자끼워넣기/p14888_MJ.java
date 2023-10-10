package p14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14888_MJ {
		static int N;
		static int[] num;
		static int[] oper;
		static int max = -1000000000;
		static int min = 1000000000;
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		oper = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
	}
	
	static void dfs(int depth, int value) {
		if(depth==N) {
			if(value > max)
				max = value;
			if(value < min)
				min = value;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i]>0) {
				oper[i]--;
				
				if(i==0)
					dfs(depth+1, value+num[depth]);
				else if(i==1)
					dfs(depth+1, value-num[depth]);
				else if(i==2)
					dfs(depth+1, value*num[depth]);
				else if(i==3) {
					if(value<0) 
						dfs(depth+1, value*(-1)/num[depth]*(-1));
					else
						dfs(depth+1, value/num[depth]);
				}
					
				oper[i]++;
			}
		}
	}
	
	public static void main(String[] args){
		try {
			input();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dfs(1, num[0]);
		
		System.out.println(max);
		System.out.println(min);

	}
}
