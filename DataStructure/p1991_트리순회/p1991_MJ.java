package DataStructure.p1991_트리순회;

import java.util.*;
import java.io.*;

public class p1991_MJ {

	static class Node {
		int left;
		int right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Node[] list;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		list = new Node[N+1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0) - 'A' + 1;
			int left = st.nextToken().charAt(0) - 'A' + 1;
			int right = st.nextToken().charAt(0) - 'A' + 1;

			list[node] = new Node(left, right);
		}
		
		preorder(1);
		sb.append("\n");
		inorder(1);
		sb.append("\n");
		postorder(1);

		System.out.println(sb.toString());
		
		br.close();
	}

	// 전위 순회 : 루트 -> 왼쪽 자식 -> 오른쪽 자식
	public static void preorder(int root) {
		
		int left = list[root].left;
		int right = list[root].right;
		
		char rootTmp = (char)(root + 'A' - 1);
		char leftTmp = (char)(left + 'A' - 1);
		char rightTmp = (char)(right + 'A' - 1);
		
		sb.append(rootTmp);
		
		if(leftTmp!='.')
			preorder(left);
		
		if(rightTmp!='.')
			preorder(right);
	}

	// 중위 순회 : 왼쪽 자식 -> 루트 -> 오른쪽 자식
	public static void inorder(int root) {

		int left = list[root].left;
		int right = list[root].right;
		
		char rootTmp = (char)(root + 'A' - 1);
		char leftTmp = (char)(left + 'A' - 1);
		char rightTmp = (char)(right + 'A' - 1);
		
		if(leftTmp!='.')
			inorder(left);
		
		sb.append(rootTmp);
		
		if(rightTmp!='.')
			inorder(right);
	}

	// 후위 순회 : 왼쪽 자식 -> 오른쪽 자식 -> 루트
	public static void postorder(int root) {

		int left = list[root].left;
		int right = list[root].right;
		
		char rootTmp = (char)(root + 'A' - 1);
		char leftTmp = (char)(left + 'A' - 1);
		char rightTmp = (char)(right + 'A' - 1);
		
		if(leftTmp!='.')
			postorder(left);
		
		if(rightTmp!='.')
			postorder(right);
		
		sb.append(rootTmp);	
	}
}
