package String.p1294_문자열장식;

import java.io.*;
import java.util.*;

public class p1294_HC {

	private static final char dummy = 'Z' + 1;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<String> heap = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			heap.add(br.readLine() + dummy);
		}

		StringBuilder sb = new StringBuilder();
		while (!heap.isEmpty()) {
			String text = heap.poll();
			sb.append(text.charAt(0));
			if (text.length() > 2) {
				heap.add(text.substring(1));
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}
}
