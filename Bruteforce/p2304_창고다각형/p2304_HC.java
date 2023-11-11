package Bruteforce.p2304_창고다각형;

import java.io.*;
import java.util.*;

public class p2304_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] heights = new int[1001];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			heights[L] = H;
		}

		int left = -1;
		int right = -1;
		int maxX = -1;
		int maxHeight = 0;
		for (int i = 0; i < 1001; ++i) {
			if (left == -1 && heights[i] > 0)
				left = i;
			if (heights[i] > 0)
				right = i;
			if (maxHeight < heights[i]) {
				maxHeight = heights[i];
				maxX = i;
			}
		}

		for (int i = left; i < maxX; ++i) {
			for (int j = i + 1; j < maxX && heights[i] > heights[j]; ++j) {
				heights[j] = heights[i];
			}
		}

		for (int i = right; i > maxX; --i) {
			for (int j = i - 1; j > maxX && heights[i] > heights[j]; --j) {
				heights[j] = heights[i];
			}
		}

		int answer = Arrays.stream(heights)
			.sum();
		System.out.println(answer);
	    br.close();
	}
}
