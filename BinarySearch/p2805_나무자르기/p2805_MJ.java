package BinarySearch.p2805_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2805_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		long tree[] = new long[N];
		long max = 0, start, end;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Long.parseLong(st.nextToken());
			max = Math.max(max, tree[i]);
		}

		start = 0;
		end = max;

		while (start <= end) {
			long mid = (start + end) / 2;
			long sum = 0;

			for (long treeOne : tree) {
				if (treeOne > mid)
					sum += treeOne - mid;
			}

			if (sum >= M)
				start = mid + 1;
			else
				end = mid - 1;
		}

		System.out.println(end);

		br.close();
	}
}
