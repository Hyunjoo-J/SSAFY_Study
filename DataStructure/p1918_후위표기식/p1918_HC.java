package DataStructure.p1918_후위표기식;

import java.io.*;
import java.util.*;

public class p1918_HC {

	private static Map<Character, Integer> priority = new HashMap<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);

		char[] expr = br.readLine().toCharArray();

		Deque<Character> stack = new ArrayDeque<>(100);
		StringBuilder sb = new StringBuilder();
		for (char c: expr) {
			switch (c) {
				case '*':
				case '/':
					while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
						Character op = stack.pop();
						sb.append(op);
					}
					stack.push(c);
					break;
				case '+':
				case '-':
					while (!stack.isEmpty() && stack.peek() != '(') {
						Character op = stack.pop();
						sb.append(op);
					}
					stack.push(c);
					break;
				case ')':
					while (!stack.isEmpty()) {
						Character op = stack.pop();
						if (op == '(')
							break;
						sb.append(op);
					}
					break;
				case '(':
					stack.push(c);
					break;
				default:
					sb.append(c);
					break;
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
		br.close();
	}
}
