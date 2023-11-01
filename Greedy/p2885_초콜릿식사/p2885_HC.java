package Greedy.p2885_초콜릿식사;

import java.io.*;

public class p2885_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());

		int b = 1;
		while (b < K)
			b <<= 1;

		int c = 0;
		if (K != b) {
			for (int i = b >> 1; K > 0; i >>= 1, ++c) {
				if (K >= i) {
					K -= i;
				}
			}
		}
		System.out.println(b + " " + c);
		br.close();
	}
}
