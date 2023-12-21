package TwoPointer.p1806_부분합;

import java.util.*;
import java.io.*;
public class p1806_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());

		long sum = 0;
		int left = 0, right = 0;
		int ans = Integer.MAX_VALUE;
		while(true){
			if(sum >= S){
				sum -= arr[left];
				ans = Math.min(right - left, ans);
				++left;
			}else if(right == N){
				break;
			}else{
				sum += arr[right];
				++right;
			}
		}
		if(ans == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(ans);
	}
}
