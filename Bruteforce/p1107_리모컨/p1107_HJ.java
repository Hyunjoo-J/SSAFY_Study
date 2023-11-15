package Bruteforce.p1107_리모컨;
import java.util.*;
import java.io.*;

public class p1107_HJ {
	static boolean[] trouble = new boolean[10];
	static int N;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		if(M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (M-- > 0)
				trouble[Integer.parseInt(st.nextToken())] = true;
		}
		if(N == 100){
			System.out.println(0);
			return;
		}

		ans = Math.abs(N - 100);
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int num, int cnt) {
		for (int i = 0; i < 10; ++i) {
			if (!trouble[i]) {
				int btn = cnt * 10 + i;
				int tmp = Math.abs(N - btn) + String.valueOf(btn).length();
				ans = Math.min(ans, tmp);
				if (num < 6)
					dfs(num + 1, btn);
			}
		}
	}
}
