package Greedy.p2138_전구와스위치;

import java.io.*;

public class p2138_HC {

	private static final int INF = Integer.MAX_VALUE >> 1;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] original = new int[N];
		int[] target = new int[N];

		String input = br.readLine();
		for (int i = 0; i < N; ++i) {
			original[i] = input.charAt(i) - '0';
		}
		input = br.readLine();
		for (int i = 0; i < N; ++i) {
			target[i] = input.charAt(i) - '0';
		}

		int first = simul(original, target, N);

		switchPush(original, N, 0);
		int second = simul(original, target, N);

		int answer = Math.min(first, second + 1);
		System.out.println(answer == INF ? -1 : answer);
		br.close();
	}

	private static int simul(int[] original, int[] target, int n) {
		int res = 0;
		int[] cloned = original.clone();
		for (int i = 0; i < n - 1; ++i) {
			if (cloned[i] != target[i]) {
				++res;
				switchPush(cloned, n, i + 1);
			}
		}

		for (int i = 0; i < n; ++i) {
			if (cloned[i] != target[i])
				return INF;
		}
		return res;
	}

	private static void switchPush(int[] switches, int n, int index) {
		if (index > 0)
			switches[index - 1] = 1 - switches[index - 1];
		switches[index] = 1 - switches[index];
		if (index < n - 1)
			switches[index + 1] = 1 - switches[index + 1];
	}
}
