package Greedy.p1541_잃어버린괄호;

import java.io.*;

public class p1541_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expr = br.readLine();

        String[] splited = expr.split("[+, -]");
        int n = splited.length;
        int[] operand = new int[n];
        for (int i = 0; i < n; ++i) {
            operand[i] = Integer.parseInt(splited[i]);
        }

        char[] operator = new char[n - 1];
        int oIdx = 0;
        for (char c: expr.toCharArray()) {
            if (c == '+' || c == '-')
                operator[oIdx++] = c;
        }

        int answer = 0;
        boolean minus = false;
        for (int i = 0; i < n; ++i) {
            answer += minus ? -operand[i] : operand[i];
            if (i < n - 1 && operator[i] == '-')
                minus = true;
        }
        System.out.println(answer);
        br.close();
    }
}
