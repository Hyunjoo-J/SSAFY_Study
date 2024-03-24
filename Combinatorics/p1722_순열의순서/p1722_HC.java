package Combinatorics.p1722_순열의순서;

import java.io.*;
import java.util.*;

public class p1722_HC {

	private static long[] factorial = new long[21];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		// dp factorial
		factorial[0] = 1;
		for (int i = 1; i < 21; ++i) {
			factorial[i] = i * factorial[i - 1];
		}

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		if (Integer.parseInt(st.nextToken()) == 1) {
			long k = Long.parseLong(st.nextToken());
			System.out.println(arrayToString(indexToSequence(n, k)));
		} else {
			int[] sequence = new int[n];
			for (int i = 0; i < n; ++i) {
				sequence[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(sequenceToIndex(n, sequence));
		}
	    br.close();
	}

	private static int[] indexToSequence(int n, long k) {
		--k;
		boolean[] used = new boolean[n + 1];
		int[] sequence = new int[n];
		for (int i = 0, q; i < n; ++i) {
			q = (int) (k / factorial[n - i - 1]);
			k -= q * factorial[n - i - 1];
			for (int j = 1; j <= n; ++j) {
				if (!used[j]) {
					if (q == 0) {
						sequence[i] = j;
						used[j] = true;
						break;
					} else --q;
				}
			}
		}
		return sequence;
	}

	private static long sequenceToIndex(int n, int[] sequence) {
		boolean[] used = new boolean[n + 1];
		long k = 1;
		for (int i = 0; i < n; used[sequence[i++]] = true) {
			k += unusedCount(used, sequence[i]) * factorial[n - i - 1];
		}
		return k;
	}

	private static int unusedCount(boolean[] used, int number) {
		int count = 0;
		for (int i = 1; i < number; ++i) {
			count += used[i] ? 0 : 1;
		}
		return count;
	}

	private static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, end = array.length; i < end; ++i)
			sb.append(array[i]).append(" ");
		return sb.toString();
	}
}
