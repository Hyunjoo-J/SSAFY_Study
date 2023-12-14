package Math.p2166_다각형의넓이;

import java.io.*;
import java.util.*;

public class p2166_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		long[] x = new long[N + 1];
		long[] y = new long[N + 1];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}
		x[N] = x[0];
		y[N] = y[0];

		double answer = 0;
		for (int i = 0; i < N; ++i) {
			answer += x[i] * y[i + 1];
		}
		for (int i = 0; i < N; ++i) {
			answer -= y[i] * x[i + 1];
		}
		System.out.printf("%.1f%n", Math.abs(answer / 2));
	    br.close();
	}
}
