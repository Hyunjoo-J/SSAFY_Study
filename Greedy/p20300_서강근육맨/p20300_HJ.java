package Greedy.p20300_서강근육맨;
import java.util.*;
import java.io.*;
public class p20300_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] lose = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			lose[i] = Long.parseLong(st.nextToken());
		Arrays.sort(lose);

		long ans = 0;
		int right = N - 1;
		if(N % 2 == 1){
			ans = lose[right];
			--right;
		}

		for(int left = 0; left < right; ++left, --right)
			ans = Math.max(ans, lose[left] + lose[right]);
		System.out.println(ans);
	}
}
