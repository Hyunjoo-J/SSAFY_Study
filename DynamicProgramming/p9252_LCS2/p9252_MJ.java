package DynamicProgramming.p9252_LCS2;

import java.util.*;
import java.io.*;

public class p9252_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String one, two;
	static int dp[][];
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		one = br.readLine();
		two = br.readLine();
		dp = new int[one.length() + 1][two.length() + 1];
		sb = new StringBuilder();

		for (int i = 1; i < one.length() + 1; i++) {
			for (int j = 1; j < two.length() + 1; j++) {
				if (one.charAt(i - 1) == two.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}

		find(one.length(), two.length(), dp[one.length()][two.length()]);
		sb.reverse();
		
		System.out.println(dp[one.length()][two.length()]);
		System.out.println(sb);
		
		br.close();
	}

	private static void find(int i, int j, int cnt) {
		if(cnt==0)
			return;
		
		if (one.charAt(i - 1) == two.charAt(j - 1)) {
			sb.append(one.charAt(i-1));
			find(i-1, j-1, cnt-1);
		}
		else {
			if(dp[i-1][j] > dp[i][j-1])
				find(i-1, j, cnt);
			else
				find(i, j-1, cnt);
		}
	}
}
