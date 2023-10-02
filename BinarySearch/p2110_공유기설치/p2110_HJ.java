package BinarySearch.p2110_공유기설치;

import java.util.*;
import java.io.*;
public class p2110_HJ {
	static int N, C;
	static  int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0; i < N; ++i){
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		System.out.println(binarySearch(1, arr[N - 1]));

	}

	//이분탐색으로 가능한 최대 거리를 찾음
	private static int binarySearch(int start, int end) {
		int ans = 0;
		while(start <= end){
			int mid = (start + end) >> 1;

			if(install(mid) < C){
				end = mid - 1;
			}else{
				ans = mid;
				start = mid + 1;
			}
		}
		return ans;
	}

	//dist 길이의 공유기로 모든 집을 커버할 수 있는가
	private static int install(int dist) {
		int cnt = 1;
		int last = arr[0];
		for(int i = 0; i < N; ++i){
			int to = arr[i];

			if(to - last >= dist){
				++cnt;
				last = to;
			}
		}
		return cnt;
	}
}
