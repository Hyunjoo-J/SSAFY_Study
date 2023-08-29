package Implematation.p16637_괄호추가하기;

import java.io.*;

public class p16637_HJ {
	static int ans = Integer.MIN_VALUE;
    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        ops = new ArrayList<Character>();
        nums = new ArrayList<Integer>();
        for(int i = 0; i < N; ++i){
            char c = line.charAt(i);
            if(c == '+' || c == '*' || c == '-'){
                ops.add(c);
            }else{
                nums.add(c - '0');
            }
        }
        dfs(nums.get(0), 0);
        System.out.println(ans);
    }
    private static void dfs(int num, int cnt){
        if(cnt >= ops.size()){
            ans = Math.max(ans, num);
            return;
        }
        int res = cal(ops.get(cnt), num, nums.get(cnt + 1));//괄호 없이 계산
        dfs(res, cnt + 1);

        if(cnt + 1 < ops.size()){
            //그 다음이 괄호에 묶였다고 가정하고 먼저 계산 ex) A + (B + C)
            res = cal(ops.get(cnt + 1), nums.get(cnt + 1), nums.get(cnt + 2)); //res = (B + C)
            dfs(cal(ops.get(cnt), num, res), cnt + 2); //A + res
        }
    }
    private static int cal(char op, int a, int b){
        switch(op){
            case '+' :
                return a + b;
            case '-':
                return a - b;
            case '*':
                    return a * b;
        }
        return -1 ;
    }
}

