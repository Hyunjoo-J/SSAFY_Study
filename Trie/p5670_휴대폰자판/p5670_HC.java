package Trie.p5670_휴대폰자판;

import java.io.*;

public class p5670_HC {

	private static TrieNode root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input;
		String[] words = new String[100001];
		StringBuilder sb = new StringBuilder();

		while ((input = br.readLine()) != null) {
			root = new TrieNode();

			int N = Integer.parseInt(input);
			for (int i = 0; i < N; ++i) {
				words[i] = br.readLine();
				insert(words[i]);
			}

			int push = 0;
			for (int i = 0; i < N; ++i) {
				push += touch(words[i]);
			}
			sb.append(String.format("%.2f", (double) push / N)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int touch(String word) {
		int len = word.length();
		int prevCount, result = 0;
		TrieNode p = root;

		if (p.count == p.children[word.charAt(0) - 'a'].count)
			++result;

		for (int i = 0; i < len; ++i) {
			prevCount = p.count;
			p = p.children[word.charAt(i) - 'a'];
			if (prevCount != p.count)
				++result;
			if (p.count == 1)
				break;
		}
		return result;
	}

	private static void insert(String word) {
		int len = word.length();
		TrieNode p = root;
		++p.count;
		for (int i = 0; i < len; ++i) {
			if (p.children[word.charAt(i) - 'a'] == null)
				p.children[word.charAt(i) - 'a'] = new TrieNode();
			p = p.children[word.charAt(i) - 'a'];
			++p.count;
		}
		p.isWord = true;
	}

	private static class TrieNode {
		boolean isWord;
		int count;
		TrieNode[] children = new TrieNode[26];
	}
}
