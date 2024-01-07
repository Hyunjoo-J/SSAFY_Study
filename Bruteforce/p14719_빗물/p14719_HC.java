package Bruteforce.p14719_빗물;

import java.io.*;
import java.util.*;

public class p14719_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] height = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; ++i) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		int[] t = new int[W];
		for (int i = 0, curr = 0; i < W; ++i) {
			curr = Math.max(height[i], curr);
			t[i] = curr;
		}
		for (int i = W - 1, curr = 0; i >= 0; --i) {
			curr = Math.max(height[i], curr);
			t[i] = Math.min(t[i], curr);
		}

		int answer = 0;
		for (int i = 0; i < W; ++i) {
			answer += t[i] - height[i];
		}
		System.out.println(answer);
	    br.close();
	}
}
