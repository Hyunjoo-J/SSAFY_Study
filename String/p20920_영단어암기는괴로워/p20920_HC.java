package String.p20920_영단어암기는괴로워;

import java.io.*;
import java.util.*;

public class p20920_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Map<String, Integer> map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; ++i) {
			String word = br.readLine();
			if (word.length() >= M) {
				map.put(word, map.getOrDefault(word, 0) + 1);
			}
		}

		int dictLength = map.size();
		Pair[] words = new Pair[dictLength];

		int idx = 0;
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			words[idx++] = (new Pair(entry.getKey(), entry.getValue()));
		}

		Arrays.sort(words, (o1, o2) -> {
			if (o1.count != o2.count) {
				return o2.count - o1.count;
			}
			if (o1.wordLength != o2.wordLength) {
				return o2.wordLength - o1.wordLength;
			}
			return o1.word.compareTo(o2.word);
		});

		StringBuilder sb = new StringBuilder();
		for (Pair pair: words) {
			sb.append(pair.word).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static class Pair {
		String word;
		int wordLength;
		int count;

		public Pair(String word, int count) {
			this.word = word;
			this.wordLength = word.length();
			this.count = count;
		}
	}
}