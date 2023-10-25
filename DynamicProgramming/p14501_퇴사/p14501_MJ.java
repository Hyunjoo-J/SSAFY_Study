package DynamicProgramming.p14501_퇴사;

import java.io.*;
import java.util.*;

public class p14501_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, work[][], max[];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		work = new int[N + 1][2];
		max = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			work[i][0] = Integer.parseInt(st.nextToken());
			work[i][1] = Integer.parseInt(st.nextToken());
		}

		if (work[0][0] <= N)
			max[work[0][0]] = work[0][1];

		for (int i = 1; i < N; i++) {
			if (max[i] < max[i - 1])
				max[i] = max[i - 1];

			int time = work[i][0];
			if (i + time > N)
				continue;

			max[i + time] = Math.max(max[i + time], max[i] + work[i][1]);
		}
		
		max[N] = Math.max(max[N], max[N - 1]);

		System.out.println(max[N]);
		
		br.close();
	}
}
