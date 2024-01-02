package DataStructure.p1874_스택수열;

import java.io.*;
import java.util.*;

public class p1874_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int now = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; ++i) {
            int k = Integer.parseInt(br.readLine());
            while (k >= now) {
               stack.push(now++);
               sb.append("+\n");
            }
            if (stack.peek() == k) {
                stack.pop();
                sb.append("-\n");
            } else { // 안되는 상황 : now 보다 작은 수이고, peek 했을 때 그 수가 아니면
                sb = new StringBuilder();
                sb.append("NO");
                break;
            }
        }

        System.out.println(sb);
        br.close();
    }
}
