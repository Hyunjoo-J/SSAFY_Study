package Math.p2166_다각형의넓이;

import java.util.*;
import java.io.*;

public class p2166_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long[] x = new long[N + 1];
		long[] y = new long[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}
		x[N] = x[0];
		y[N] = y[0];

		double ans = 0;
		for(int i = 0; i < N; ++i){
			ans += x[i] * y[i + 1];
			ans -= x[i + 1] * y[i];
		}
		System.out.printf("%.1f", Math.abs(ans) / 2);
	}
}
