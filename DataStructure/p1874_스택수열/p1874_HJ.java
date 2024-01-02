package DataStructure.p1874_스택수열;
import java.util.*;
import java.io.*;
public class p1874_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		int[] res = new int[N];
		for(int i = 0; i < N; ++i){
			res[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0, num = 1; i < N; ++i){
			while(res[i] >= num){
				stack.push(num);
				++num;
				sb.append("+\n");
			}
			if(stack.isEmpty() || stack.peek() != res[i]){
				System.out.println("NO");
				return;
			} else{
				stack.pop();
				sb.append("-\n");
			}
		}
		System.out.print(sb);
	}
}
