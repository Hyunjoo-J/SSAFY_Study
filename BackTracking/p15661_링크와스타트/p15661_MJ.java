package BackTracking.p15661_링크와스타트;

import java.util.*;
import java.io.*;

public class p15661_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S[][];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int team1, team2, diff, min = Integer.MAX_VALUE;
		// 2진수로 만들어서 team1, team2 팀 조합 구하기
		for (int i = 1; i < ((1 << N) - 1); i++) {
			team1 = i;
			team2 = ((1 << N) - 1) - i;

			diff = Math.abs(skillSum(team1) - skillSum(team2));

			if (min > diff)
				min = diff;
		}

		System.out.println(min);

		br.close();
	}

	private static int skillSum(int n) {
		String Binary = Integer.toBinaryString(n);
		
		// "1"을 "0001"로 만들어주기 위해 
		int length = Binary.length();
		for (int i = 0; i < N - length; i++) {
			Binary = '0' + Binary;
		}

		int sum = 0;
		for (int i = 0; i < Binary.length(); i++) {
			if (Binary.charAt(i) == '1') {
				for (int j = 0; j < Binary.length(); j++) {
					if (Binary.charAt(j) == '1') {
						sum += S[i][j];
					}
				}
			}
		}
		return sum;
	}
}
