package DynamicProgramming.p17404_RGB거리2;

import java.util.*;
import java.io.*;

public class P17404_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int house[][], min[][], N, ans = 1000001;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		house = new int[N + 1][3];
		min = new int[N + 1][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 3; i++)
			paint(i);

		System.out.println(ans);

		br.close();
	}

	private static void paint(int first) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				min[i][j] = house[i][j];
			}
		}

		for (int i = 0; i < 3; i++) {
			if (i != first)
				min[0][i] = 1001;
		}

		for (int i = 1; i < N; i++) {
			min[i][0] += Math.min(min[i - 1][1], min[i - 1][2]);
			min[i][1] += Math.min(min[i - 1][0], min[i - 1][2]);
			min[i][2] += Math.min(min[i - 1][0], min[i - 1][1]);
		}

		for (int i = 0; i < 3; i++) {
			if (i != first && ans > min[N - 1][i])
				ans = min[N - 1][i];
		}
	}
}
