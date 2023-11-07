package Tree.p5639_이진검색트리;
import java.io.*;
public class p5639_HJ {
	static class Node{
		int num;
		Node left, right;
		Node(int num){
			this.num = num;
		}
		Node(int num, Node left, Node right){
			this.num = num;
			this.left = left;
			this.right = right;
		}

		void insert(int n){
			if(n < this.num){
				if(this.left ==null)
					this.left = new Node(n);
				else this.left.insert(n);
			}else{
				if(this.right == null)
					this.right = new Node(n);
				else this.right.insert(n);
			}
		}
	}

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));
		String input;
		while(true){
			input = br.readLine();
			if(input == null || input.equals(""))
				break;
			root.insert(Integer.parseInt(input));
		}
		postOrder(root);
		System.out.println(sb);
	}

	private static void postOrder(Node node){
		if(node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.num).append("\n");
	}
}