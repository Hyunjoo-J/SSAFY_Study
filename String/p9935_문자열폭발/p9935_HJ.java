package String.p9935_문자열폭발;

import java.util.*;
import java.io.*;
public class p9935_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String bomb = br.readLine();
		int size = bomb.length();

		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < line.length(); ++i){
			stack.push(line.charAt(i));

			if(stack.size() >= size){
				boolean flag = true;
				for(int j = 0; j < size; ++j){
					if(stack.get(stack.size() - size + j) != bomb.charAt(j)){
						flag = false;
						break;
					}
				}
				if(flag){
					for(int j = 0; j <size; ++j){
						stack.pop();
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(Character c : stack)
			sb.append(c);
		System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
	}
}