package Implematation.p16637_괄호추가하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p16637_YK {
    static int N, result = Integer.MIN_VALUE;
    static int[] digits;
    static char[] ops;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(br.readLine());
            return;
        }

        digits = new int[N / 2 + 1];
        ops = new char[N / 2];

        String line = br.readLine();
        digits[0] = Integer.parseInt(String.valueOf(line.charAt(0)));
        for (int i = 1; i < N; i += 2) {
            ops[(i - 1) / 2] = line.charAt(i);
            digits[(i + 1) / 2] = Integer.parseInt(String.valueOf(line.charAt(i + 1)));
        }

        dfs(digits[0], 0);
        System.out.println(result);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int num, int cnt) {
        // 연산 종료
        if (cnt >= N / 2) {
            result = Math.max(num, result);
            return;
        }

        // 괄호 없이, 순서대로
        int tmp = calculate(num, digits[cnt + 1], ops[cnt]);
        dfs(tmp, cnt + 1);

        // 뒤에 괄호가 있다고 가정
        if (cnt + 1 < N / 2) {
            tmp = calculate(digits[cnt + 1], digits[cnt + 2], ops[cnt + 1]);
            tmp = calculate(num, tmp, ops[cnt]);
            dfs(tmp, cnt + 2);
        }
    }

    private static int calculate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*' :
                return a * b;
            default:
                return 0;
        }
    }

}
