package DataStructure.p1991_트리순회;

import java.io.*;
import java.util.*;

public class p1991_YK {

    static class Tree {
        TreeNode root;
        TreeNode[] alphabetic;

        public void addNode(char name, char left, char right) {
            TreeNode node = alphabetic[name - 'A'] == null ? alphabetic[name - 'A'] = new TreeNode(name) : alphabetic[name - 'A'];
            if (left != '.') node.left = alphabetic[left - 'A'] == null ? alphabetic[left - 'A'] = new TreeNode(left) : alphabetic[left - 'A'];
            if (right != '.') node.right = alphabetic[right - 'A'] == null ? alphabetic[right - 'A'] = new TreeNode(right) : alphabetic[right - 'A'];
        }

        public String preorder() {
            StringBuilder sb = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while(!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                sb.append(cur.name);
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
            return sb.toString();
        }

        public String inorder() {
            StringBuilder sb = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode tmp = root;

            while (!stack.isEmpty() || tmp != null) {
                if (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                } else {
                    tmp = stack.pop();
                    sb.append(tmp.name);
                    tmp = tmp.right;
                }
            }

            return sb.toString();
        }

        public String postorder() {
            StringBuilder sb = new StringBuilder();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;

            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    TreeNode tmp = stack.peek().right;
                    if (tmp != null) {
                        cur = tmp;
                    } else {
                        tmp = stack.pop();
                        sb.append(tmp.name);

                        while (!stack.isEmpty() && tmp == stack.peek().right) {
                            tmp = stack.pop();
                            sb.append(tmp.name);
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
    static class TreeNode {
        char name;
        TreeNode left;
        TreeNode right;

        public TreeNode(char name) {
            this.name = name;
        }
    }

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        Tree T = new Tree();
        T.alphabetic = new TreeNode[N];
        T.root = new TreeNode('A');
        T.alphabetic[0] = T.root;

        char name, left, right;
        for (int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);
            T.addNode(name, left, right);
        }

        bw.write(T.preorder() + "\n");
        bw.write(T.inorder() + "\n");
        bw.write(T.postorder());

        bw.flush();
        bw.close();
        br.close();
    }
}
