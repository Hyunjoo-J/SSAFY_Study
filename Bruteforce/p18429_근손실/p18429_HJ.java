package Bruteforce.p18429_근손실;
import java.io.*;
import java.util.*;

public class p18429_HJ {
	static int N, K;
	static int[] weight;
	static int[] perm;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weight = new int[N];
		perm = new int[N];
		for (int i = 0; i < N; ++i) {
			perm[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			weight[i] = Integer.parseInt(st.nextToken());
		int ans = 0;
		do{
			if(check()) ans++;
		}while(np(perm, N));
		System.out.println(ans);
	}
	private static boolean check(){
		int sum = 500;
		for(int i = 0; i < N; ++i){
			sum +=(weight[perm[i]] - K);
			if(sum < 500) return false;
		}
		return true;
	}

	private static boolean np(int[] perm, int n) {
		int i = n - 1;
		while (i > 0 && perm[i - 1] > perm[i])
			--i;

		if (i == 0)
			return false;

		int j = n - 1;
		while (perm[i - 1] > perm[j])
			--j;

		swap(perm, i - 1, j);

		int k = n - 1;
		while (i < k)
			swap(perm, i++, k--);

		return true;
	}
	private static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
