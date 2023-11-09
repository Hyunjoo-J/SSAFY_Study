package String.p9935_문자열폭발;

import java.io.*;

public class p9935_HC {

	private static final String NO_RESULT = "FRULA";

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int n = text.length;
		int m = pattern.length;
		char[] stack = new char[n + 1];
		int top = 0;

		for (char c: text) {
			stack[top++] = c;
			if (top >= m) {
				boolean match = true;
				for (int i = top - m, j = 0; i < top; ++i, ++j) {
					if (stack[i] != pattern[j]) {
						match = false;
						break;
					}
				}
				if (match) {
					top = top - m;
				}
			}
		}
		if (top == 0) {
			System.out.println(NO_RESULT);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < top; ++i)
				sb.append(stack[i]);
			System.out.println(sb.toString());
		}
	    br.close();
	}
}
