package DataStructure.p1991_트리순회;
import java.util.*;
import java.io.*;

class Tree_HJ{
	private static class TreeNode{
		char ch;
		TreeNode left;
		TreeNode right;

		public TreeNode(char ch, TreeNode left, TreeNode right) {
			this.ch = ch;
			this.left = left;
			this.right = right;
		}
	}
	private final TreeNode[] idx;

	Tree_HJ(){
		this.idx = new TreeNode[26];
		this.idx[0] = new TreeNode('A', null, null);
	}

	void add(char p, char c, boolean left){
		TreeNode child = idx[c - 'A'] == null
				? idx[c - 'A'] = new TreeNode(c, null, null)
				: idx[c - 'A'];
		TreeNode parent = idx[p - 'A'] == null
				? idx[p - 'A'] = new TreeNode(p, null, null)
				: idx[p - 'A'];
		if(left)
			parent.left = child;
		else
			parent.right = child;
	}
	String preOrder(){
		StringBuilder sb = new StringBuilder();
		preOrder(idx[0], sb);
		return sb.toString();
	}
	private void preOrder(TreeNode tn, StringBuilder sb){
		if(tn == null)
			return;
		sb.append(tn.ch);
		preOrder(tn.left, sb);
		preOrder(tn.right, sb);
	}

	String inOrder(){
		StringBuilder sb = new StringBuilder();
		inOrder(idx[0], sb);
		return sb.toString();
	}
	private void inOrder(TreeNode tn, StringBuilder sb){
		if(tn == null)
			return;
		inOrder(tn.left, sb);
		sb.append(tn.ch);
		inOrder(tn.right, sb);
	}

	String postOrder(){
		StringBuilder sb = new StringBuilder();
		postOrder(idx[0], sb);
		return sb.toString();
	}
	private void postOrder(TreeNode tn, StringBuilder sb){
		if(tn == null)
			return;
		postOrder(tn.left, sb);
		postOrder(tn.right, sb);
		sb.append(tn.ch);
	}
}
public class p1991_HJ {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Tree_HJ tree = new Tree_HJ();
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			char ch = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			if(left != '.')
				tree.add(ch, left, true);
			if(right != '.')
				tree.add(ch, right, false);
		}
		System.out.println(tree.preOrder());
		System.out.println(tree.inOrder());
		System.out.println(tree.postOrder());
	}
}

