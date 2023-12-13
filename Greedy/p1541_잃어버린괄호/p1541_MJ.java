package Greedy.p1541_잃어버린괄호;

import java.util.*;
import java.io.*;

public class p1541_MJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer minus = new StringTokenizer(br.readLine(), "-");
		
		int ans = Integer.MAX_VALUE;
		while(minus.hasMoreTokens()) {
			StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+");
			int sum = 0;
			while(plus.hasMoreTokens()) {
				sum += Integer.parseInt(plus.nextToken());
			}
			
			if(ans==Integer.MAX_VALUE)
				ans = sum;
			else
				ans -= sum;
		}
		
		System.out.println(ans);
	
		br.close();
	}
}
