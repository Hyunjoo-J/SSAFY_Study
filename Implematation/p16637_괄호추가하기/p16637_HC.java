package Implematation.p16637_괄호추가하기;

import java.io.*;

public class p16637_HC {

    private static int N, N2, answer = Integer.MIN_VALUE;
    private static int[] operand;
    private static char[] operator;
    private static boolean[] bracket;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        N2 = N >> 1;
        operand = new int[N2 + 1];
        bracket = new boolean[N2];
        operator = new char[N2];

        String expr = br.readLine();
        for (int i = 0; i < N; ++i) {
            if ((i & 1) == 0)
                operand[i >> 1] = expr.charAt(i) - '0';
            else
                operator[i >> 1] = expr.charAt(i);
        }
        dfs(0);
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth) {
        if (depth >= N2) {
            answer = Math.max(answer, simul());
            return;
        }

        bracket[depth] = true;
        dfs(depth + 2);

        bracket[depth] = false;
        dfs(depth + 1);
    }

    private static int simul() {
        int[] nums = operand.clone();
        char[] ops = operator.clone();
        for (int i = 0; i < N2; ++i) {
            if (bracket[i]) {
                nums[i] = operation(nums[i], nums[i + 1], ops[i]);
                nums[i + 1] = 0;
                ops[i] = '+';
            }
        }

        int res = nums[0];
        for (int i = 0; i < N2; ++i) {
            res = operation(res, nums[i + 1], ops[i]);
        }
        return res;
    }

    private static int operation(int num1, int num2, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
        }
        return res;
    }
}

