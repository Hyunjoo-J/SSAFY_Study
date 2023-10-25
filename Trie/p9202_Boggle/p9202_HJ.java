package Trie.p9202_Boggle;
import java.util.*;
import java.io.*;

public class p9202_HJ {
	static class TrieNode{
		boolean isWord = false;
		boolean isDirty = false;
		TrieNode[] children = new TrieNode[26];

	}

	private static final int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
	private static final int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};
	private static final int[] score = {0, 0, 0, 1, 1, 2, 3 ,5, 11};
	private static final TrieNode root = new TrieNode();
	private static final char[][] grid = new char[4][4];
	private static final boolean[][] visited = new boolean[4][4];
	private static int max;
	private static String longest = "";
	private static int wordcnt;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int w = Integer.parseInt(br.readLine());
		for(int i = 0; i < w; ++i)
			insert(br.readLine());
		br.readLine();
		int b = Integer.parseInt(br.readLine());
		while(b-- > 0){
			for(int i = 0; i < 4; ++i){
				String line = br.readLine();
				for(int j = 0; j < 4; ++j){
					grid[i][j] = line.charAt(j);
				}
			}

			for(int i = 0; i < 4; ++i){
				for(int j = 0; j < 4; ++j){
					dfs(root, i, j, new StringBuilder());
				}
			}
			sb.append(max).append(" ").append(longest).append(" ").append(wordcnt).append("\n");
			clear(root);
			max = 0;
			longest = "";
			wordcnt = 0;

			if(b > 0)
				br.readLine();

		}
		System.out.print(sb);
	}

	private static void clear(TrieNode node) {
		node.isDirty = false;
		for(int i = 0; i < 26; ++i){
			if(node.children[i] != null)
				clear(node.children[i]);
		}
	}

	private static void dfs(TrieNode node, int x, int y, StringBuilder sb) {
		node = node.children[grid[x][y] - 'A'];
		if(node == null)
			return;
		visited[x][y] = true;
		sb.append(grid[x][y]);
		if(node.isWord && !node.isDirty){
			node.isDirty = true;
			++wordcnt;
			max += score[sb.length()];
			longest = isLong(longest, sb.toString());
		}

		int nx, ny;
		for(int i = 0; i < 8; ++i){
			nx = x + dx[i];
			ny = y + dy[i];

			if(!isRange(nx, ny))
				continue;
			if(visited[nx][ny])
				continue;
			dfs(node, nx, ny, sb);
		}
		visited[x][y] = false;
		sb.deleteCharAt(sb.length() - 1);
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}

	private static String isLong(String a, String b) {
		if(a.length() > b.length())
			return a;
		else if(a.length() < b.length())
			return b;
		else
			return a.compareTo(b) <= 0 ? a : b;
	}

	private static void insert(String word){
		int len = word.length();
		TrieNode p = root;
		for(int i = 0; i < len; ++i){
			if(p.children[word.charAt(i) - 'A'] == null)
				p.children[word.charAt(i) - 'A'] = new TrieNode();
			p = p.children[word.charAt(i) - 'A'];
		}
		p.isWord = true;
	}
}
