package DataStructure.p1918_후위표기식;
import java.util.*;
import java.io.*;
public class p1918_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();

		Stack<Character> stack = new Stack<>();
		int len = str.length();
		for(int i = 0; i < len; ++i){
			char now = str.charAt(i);

			switch(now){
				case'+':
				case'-':
				case'*':
				case'/':
					while (!stack.isEmpty() && priority(stack.peek()) >= priority(now)) {
						sb.append(stack.pop());
					}
					stack.add(now);
					break;
				case '(':
					stack.add(now);
					break;
				case ')':
					while(!stack.isEmpty() && stack.peek() != '('){
						sb.append(stack.pop());
					}
					stack.pop();
					break;
				default:
					sb.append(now);

			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}

	private static int priority(char operator){

		if(operator=='(' || operator==')'){
			return 0;
		} else if (operator == '+' || operator == '-') {
			return 1;
		} else if (operator == '*' || operator == '/') {
			return 2;
		}
		return -1;
	}
}
