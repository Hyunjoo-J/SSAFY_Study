package Bruteforce.p20529_가장가까운세사람의심리적거리;

import java.util.*;
import java.io.*;

public class p20529_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String mbti[];
	static int ans, N;

	public static void main(String[] args) throws Exception {
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;

			mbti = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				mbti[i] = st.nextToken();
			}

			// 33개부터는 같은 MBTI가 3개가 무조건 나오기 때문에 답은 0이 된다.
			if (N > 32) {
				System.out.println(0);
				continue;
			}

			combi(0, 0);

			System.out.println(ans);

		}
		br.close();
	}

	// 조합 구하기
	static int number[] = new int[3];

	private static void combi(int cnt, int num) {
		if (ans == 0)
			return;

		if (cnt == 3) {
			int dist = count();
			if (dist < ans)
				ans = dist;
			return;
		}

		if (num >= N)
			return;

		number[cnt] = num;
		combi(cnt + 1, num + 1);

		combi(cnt, num + 1);
	}

	// 3개의 MBTI 거리 비교하기
	private static int count() {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			if (mbti[number[0]].charAt(i) != mbti[number[1]].charAt(i))
				cnt++;
			if (mbti[number[1]].charAt(i) != mbti[number[2]].charAt(i))
				cnt++;
			if (mbti[number[2]].charAt(i) != mbti[number[0]].charAt(i))
				cnt++;
		}

		return cnt;
	}
}
