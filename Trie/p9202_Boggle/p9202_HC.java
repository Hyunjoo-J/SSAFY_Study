package Trie.p9202_Boggle;

import java.io.*;

public class p9202_HC {

	private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
	private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	private static final int[] score = {0, 0, 0, 1, 1, 2, 3 ,5, 11};
	private static final TrieNode root = new TrieNode();
	private static final char[][] grid = new char[4][4];
	private static final boolean[][] visited = new boolean[4][4];
	private static int maxScore;
	private static int findWordCnt;
	private static String longestWord = "";

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int w = Integer.parseInt(br.readLine());
		for (int i = 0; i < w; ++i) {
			insert(br.readLine());
		}
		br.readLine();

		int b = Integer.parseInt(br.readLine());
		while (b-- > 0) {
			for (int i = 0; i < 4; ++i) {
				String line = br.readLine();
				for (int j = 0; j < 4; ++j) {
					grid[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					dfs(root, i, j, new StringBuilder());
				}
			}

			sb.append(maxScore).append(" ")
					.append(longestWord).append(" ")
					.append(findWordCnt).append("\n");

			clear(root);
			maxScore = 0;
			findWordCnt = 0;
			longestWord = "";

			if (b > 0)
				br.readLine();
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static void dfs(TrieNode node, int x, int y, StringBuilder sb) {
		node = node.children[grid[x][y] - 'A'];
		if (node == null)
			return;

		visited[x][y] = true;
		sb.append(grid[x][y]);

		if (node.isWord && !node.isDirty) {
			node.isDirty = true;
			++findWordCnt;
			maxScore += score[sb.length()];
			longestWord = getHighPriority(longestWord, sb.toString());
		}

		int nx, ny;
		for (int i = 0; i < 8; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (!isRange(nx, ny))
				continue;
			if (visited[nx][ny])
				continue;
			dfs(node, nx, ny, sb);
		}
		visited[x][y] = false;
		sb.deleteCharAt(sb.length() - 1);
	}

	private static void insert(String word) {
		int len = word.length();
		TrieNode p = root;
		for (int i = 0; i < len; ++i) {
			if (p.children[word.charAt(i) - 'A'] == null)
				p.children[word.charAt(i) - 'A'] = new TrieNode();
			p = p.children[word.charAt(i) - 'A'];
		}
		p.isWord = true;
	}

	private static void clear(TrieNode node) {
		node.isDirty = false;
		for (int i = 0; i < 26; ++i) {
			if (node.children[i] != null)
				clear(node.children[i]);
		}
	}

	private static String getHighPriority(String word1, String word2) {
		if (word1.length() > word2.length())
			return word1;
		else if (word1.length() < word2.length())
			return word2;
		return word1.compareTo(word2) <= 0 ? word1 : word2;
	}

	private static boolean isRange(int x, int y) {
		return 0 <= x && x < 4 && 0 <= y && y < 4;
	}

	private static class TrieNode {
		boolean isWord = false;
		boolean isDirty = false;
		TrieNode[] children = new TrieNode[26];
	}
}
