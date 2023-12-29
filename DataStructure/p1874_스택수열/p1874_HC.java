package DataStructure.p1874_스택수열;

import java.io.*;
import java.util.*;

public class p1874_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] target = new int[N];

		for (int i = 0; i < N; ++i) {
			target[i] = Integer.parseInt(br.readLine());
		}

		boolean isPossible = true;
		StringBuilder sb = new StringBuilder();
		Deque<Integer> stack = new ArrayDeque<>(N + 1);
		for (int i = 0, num = 1; i < N; ++i) {
			while (target[i] >= num) {
				stack.push(num);
				++num;
				sb.append("+\n");
			}

			if (stack.isEmpty() || stack.peek() != target[i]) {
				isPossible = false;
				break;
			} else {
				stack.pop();
				sb.append("-\n");
			}
		}
		System.out.println(isPossible ? sb.toString() : "NO");
	    br.close();
	}
}
