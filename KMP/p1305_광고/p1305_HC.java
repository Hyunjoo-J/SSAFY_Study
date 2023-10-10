package KMP.p1305_광고;

import java.io.*;

public class p1305_HC {

	private static int[] failure(String pattern, int n) {
		int[] table = new int[n];
		int j = 0;
		for (int i = 1; i < n; ++i) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = table[j - 1];

			if (pattern.charAt(i) == pattern.charAt(j)) {
				table[i] = ++j;
			}
		}
		return table;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String pattern = br.readLine();
		int[] table = failure(pattern, L);
		System.out.println(L - table[L - 1]);
		br.close();
	}
}
