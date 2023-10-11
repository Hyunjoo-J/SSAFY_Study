package TwoPointer.p22857_가장긴짝수연속한부분수열small;

import java.io.*;
import java.util.*;

public class p22857_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, num[], diff[], max = 0;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int i = 0;
		int j = 0;
		int cntOdd = 0;
		int cntEven = 0;

		while (j < N) {
			if (cntOdd > K) {
				max = Math.max(max, cntEven);
				// 홀수를 하나 버릴 수 있을 때까지 i 이동
				while (num[i] % 2 == 0) {
					i++;
					cntEven--;
				}
				i++;
				cntOdd--;
			} else {
				if (num[j] % 2 != 0)
					cntOdd++;
				else
					cntEven++;
				j++;

				if (j == N)
					max = Math.max(max, cntEven);
			}
		}

		System.out.println(max);
	}
}
