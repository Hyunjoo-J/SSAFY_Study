package DataStructure.p1991_트리순회;

import java.io.*;
import java.util.*;

public class p1991_HC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Tree tree = new Tree();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			char p = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			if (l != '.')
				tree.addChild(p, l, true);
			if (r != '.')
				tree.addChild(p, r, false);
		}
		System.out.println(tree.preOrder());
		System.out.println(tree.inOrder());
		System.out.println(tree.postOrder());
		br.close();
	}
}

class Tree {

	private final TreeNode[] indexer;

	Tree() {
		this.indexer = new TreeNode[26];
		this.indexer[0] = new TreeNode('A', null, null);
	}

	void addChild(char p, char c, boolean isLeft) {
		TreeNode child = indexer[c - 'A'] == null
			? indexer[c - 'A'] = new TreeNode(c, null, null)
			: indexer[c - 'A'];
		TreeNode parent = indexer[p - 'A'] == null
			? indexer[p - 'A'] = new TreeNode(p, null, null)
			: indexer[p - 'A'];
		if (isLeft)
			parent.left = child;
		else
			parent.right = child;
	}

	String preOrder() {
		StringBuilder sb = new StringBuilder();
		preOrder(indexer[0], sb);
		return sb.toString();
	}

	private void preOrder(TreeNode node, StringBuilder sb) {
		if (node == null)
			return;
		sb.append(node.c);
		preOrder(node.left, sb);
		preOrder(node.right, sb);
	}

	String inOrder() {
		StringBuilder sb = new StringBuilder();
		inOrder(indexer[0], sb);
		return sb.toString();
	}

	private void inOrder(TreeNode node, StringBuilder sb) {
		if (node == null)
			return;
		inOrder(node.left, sb);
		sb.append(node.c);
		inOrder(node.right, sb);
	}

	String postOrder() {
		StringBuilder sb = new StringBuilder();
		postOrder(indexer[0], sb);
		return sb.toString();
	}

	private void postOrder(TreeNode node, StringBuilder sb) {
		if (node == null)
			return;
		postOrder(node.left, sb);
		postOrder(node.right, sb);
		sb.append(node.c);
	}

	private static class TreeNode {
		char c;
		TreeNode left;
		TreeNode right;

		TreeNode(char c, TreeNode left, TreeNode right) {
			this.c = c;
			this.left = left;
			this.right = right;
		}
	}
}