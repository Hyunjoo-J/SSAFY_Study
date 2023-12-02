package Bruteforce.p20529_가장가까운세사람의심리적거리;

import java.io.*;
import java.util.*;

public class p20529_HC {

	private static int size, answer;
	private static final int[] comb = new int[3];
	private static final List<String> list = new ArrayList<>();
	private static final String[] mbti = new String[] {"ISTJ", "ISFJ", "INFJ", "INTJ",
														"ISTP", "ISFP", "INFP", "INTP",
														"ESTP", "ESFP", "ENFP", "ENTP",
														"ESTJ", "ESFJ", "ENFJ", "ENTJ"};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		Map<String, Integer> mbtiNumberMapper = new HashMap<>();
		for (int i = 0; i < 16; ++i)
			mbtiNumberMapper.put(mbti[i], i);

		int N;
		int[] counter = new int[16];
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			// initialize
			Arrays.fill(counter, 0);
			list.clear();
			answer = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < N; ++i) {
				String mbti = st.nextToken();
				if (counter[mbtiNumberMapper.get(mbti)]++ < 3) {
					list.add(mbti);
				}
			}
			size = list.size();
			dfs(0, 0);
			bw.write(answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int idx, int depth) {
		if (depth == 3) {
			answer = Math.min(answer, getAnswer());
			return;
		}
		for (int i = idx; i < size; ++i) {
			comb[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}

	private static int getAnswer() {
		int res = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = i + 1; j < 3; ++j) {
				res += getDistance(list.get(comb[i]), list.get(comb[j]));
			}
		}
		return res;
	}

	private static int getDistance(String mbti1, String mbti2) {
		int distance = 0;
		for (int i = 0; i < 4; ++i) {
			if (mbti1.charAt(i) != mbti2.charAt(i))
				++distance;
		}
		return distance;
	}
}