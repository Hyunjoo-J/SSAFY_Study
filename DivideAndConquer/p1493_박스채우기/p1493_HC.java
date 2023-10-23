package DivideAndConquer.p1493_박스채우기;

import java.io.*;
import java.util.*;

public class p1493_HC {

	private static final int[] cube = new int[20];
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int length = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		divideAndConquer(length, width, height);
		System.out.println(cnt);
		br.close();
	}

	private static void divideAndConquer(int length, int width, int height) {
		if (cnt == -1)
			return;
		int min = Math.min(length, Math.min(width, height));
		if (min == 0)
			return;

		int i = 19;
		while (i >= 0) {
			if (cube[i] > 0 && (1 << i) <= min) {
				break;
			}
			--i;
		}

		if (i == -1) {
			cnt = -1;
			return;
		}

		++cnt;
		--cube[i];

		int edge = 1 << i;
		divideAndConquer(length, width, height - edge);
		divideAndConquer(length - edge, width, edge);
		divideAndConquer(edge, width - edge, edge);
	}
}