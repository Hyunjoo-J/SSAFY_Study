package KMP.p1305_광고;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p1305_YK {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		
		int[] p = new int[N];
		int j = 0;
		for (int i = 1; i < N; ++i) {
			while (j > 0 && input[i] != input[j]) {
				j = p[j - 1];
			}
			if (input[i] == input[j]) p[i] = ++j;
		}
		
		System.out.println(N - p[N - 1]);
		br.close();
	}
}
