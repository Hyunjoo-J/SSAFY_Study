package Tree.p5639_이진검색트리;

import java.io.*;

public class p5639_HC {

	private static int n;
	private static int[] preOrder = new int[10010];
	private static int dIdx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		while ((input = br.readLine()) != null) {
			preOrder[n++] = Integer.parseInt(input);
		}
		dIdx = 0;
		TreeNode root = new TreeNode(preOrder[dIdx++]);
		constructTreeFromPreOrder(root, 0, 1234567);
		System.out.println(root.postOrder());
		br.close();
	}

	private static void constructTreeFromPreOrder(TreeNode node, int low, int high) {
		if (dIdx < n) {
			int current = preOrder[dIdx];
			if (low < current && current < node.value) {
				++dIdx;
				node.leftChild = new TreeNode(current);
				constructTreeFromPreOrder(node.leftChild, low, node.value);
			}
		}

		if (dIdx < n) {
			int current = preOrder[dIdx];
			if (node.value < current && current < high) {
				++dIdx;
				node.rightChild = new TreeNode(current);
				constructTreeFromPreOrder(node.rightChild, node.value, high);
			}
		}
	}

	private static class TreeNode {
		int value;
		TreeNode leftChild;
		TreeNode rightChild;

		public TreeNode(int value) {
			this.value = value;
		}

		public String postOrder() {
			StringBuilder sb = new StringBuilder();
			postOrder(this, sb);
			return sb.toString();
		}

		private static void postOrder(TreeNode node, StringBuilder sb) {
			if (node == null)
				return;
			postOrder(node.leftChild, sb);
			postOrder(node.rightChild, sb);
			sb.append(node.value).append("\n");
		}
	}
}