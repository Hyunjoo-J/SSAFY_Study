package Greedy.p1541_잃어버린괄호;

import java.io.*;

public class p1541_YK {

    static char[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();

        int[] nums = new int[25];
        char[] ops = new char[25];
        int numCnt = 0;
        int opCnt = 0;

        int cnt = 0;
        for (int i = 0, size = input.length - 1; i < size; ++i) {
            ++cnt;
            if (input[i + 1] == '-' || input[i + 1] == '+') {
                ops[opCnt++] = input[i + 1];
                nums[numCnt++] = getNum(i, cnt);
                cnt = 0;
                ++i;
            }
        }

        // 마지막 숫자
        nums[numCnt++] = getNum(input.length - 1, cnt + 1);

        // Q. 만약에 괄호를 딱 한 개만 칠 수 있다면 어떻게 풀어야 할까요?

        int result = nums[0];
        int i = 1, j = 0;
        while (i < numCnt) {
            if (ops[j] == '-') {
                int tmp = nums[i++];
                while (j < opCnt && ops[++j] == '+') {
                    tmp += nums[i++];
                }
                result -= tmp;
            } else {
                result += nums[i++];
                ++j;
            }
        }

        System.out.println(result);
        br.close();
    }

    private static int getNum(int k, int cnt) {
        int ans = 0;
        int mul = 1;
        for (int i = k; i > k - cnt; --i) {
            ans += (input[i] - '0') * mul;
            mul *= 10;
        }
        return ans;
    }

}
