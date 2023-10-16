package BinarySearch.p2473_세용액;

import java.io.*;
import java.util.*;
public class p2473_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		int[] three = new int[3];
		long min = Long.MAX_VALUE;
		for(int i = 0; i < N - 2; ++i){
			int left = i;
			int mid = i + 1;
			int right = N - 1;
			while(mid < right){
				long sum = arr[left] + arr[right] + arr[mid];
				if(min > Math.abs(sum)){
					min = Math.abs(sum);
					three[0] = left;
					three[1] = mid;
					three[2] = right;
				}
				if(sum == 0){
					break;
				}else if(sum > 0){
					--right;
				}else{
					++mid;
				}
			}
		}
		Arrays.sort(three);
		System.out.println(arr[three[0]] + " " + arr[three[1]] + " " + arr[three[2]]);
	}
}
