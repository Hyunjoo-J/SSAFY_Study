package KMP.p11585_속타는저녁메뉴;

import java.io.*;
import java.util.*;

public class p11585_HC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		char[] text = new char[(N << 1) - 1];
		char[] pattern = new char[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; ++i) {
			text[i] = st.nextToken().charAt(0);
			text[N + i] = text[i];
		}
		text[N - 1] = st.nextToken().charAt(0);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			pattern[i] = st.nextToken().charAt(0);
		}

		int ja = KMP(text, pattern);
		int gcd = gcd(ja, N);
		System.out.println((ja / gcd) + "/" + (N / gcd));
		br.close();
	}

	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}

	private static int KMP(char[] text, char[] pattern) {
		int res = 0;
		int tLen = text.length;
		int pLen = pattern.length;
		int[] table = failure(pattern, pLen);

		int j = 0;
		for (int i = 0; i < tLen; ++i) {
			while (j > 0 && text[i] != pattern[j])
				j = table[j - 1];

			if (text[i] == pattern[j]) {
				++j;
				if (j == pLen) {
					++res;
					j = table[j - 1];
				}
			}
		}
		return res;
	}

	private static int[] failure(char[] pattern, int n) {
		int[] table = new int[n];

		int j = 0;
		for (int i = 1; i < n; ++i) {
			while (j > 0 && pattern[i] != pattern[j])
				j = table[j - 1];

			if (pattern[i] == pattern[j]) {
				table[i] = ++j;
			}
		}
		return table;
	}
}