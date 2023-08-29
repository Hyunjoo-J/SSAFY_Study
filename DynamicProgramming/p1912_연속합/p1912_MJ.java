package Silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1912_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, num[], max[], ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		max = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		ans = max[0] = num[0];
		for (int i = 1; i < N; i++) {
			max[i] = (max[i - 1] > 0) ? (max[i - 1] + num[i]) : num[i];
			if (ans < max[i])
				ans = max[i];
		}

		System.out.println(ans);

		br.close();
	}
}
