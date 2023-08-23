package DynamicProgramming.p2533_사회망서비스;

import java.util.*;
import java.io.*;

public class p2533_HJ {
    static int n;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2]; //얼리어답터일 때, 아닐 때
        for(int i = 0; i <= n; ++i)
            list.add(new ArrayList<>());
        for(int i = 0; i < n - 1; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        SNS(1, -1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    //SNS(dfs)를 계속 진행하다 보면 리프노드가 0,1값을 가지는 것부터 꾸준히 올라와서 나머지 dp의 값을 채워줌
    private static void SNS(int cur, int pre){
        dp[cur][0] = 0;//얼리어답터가 아님
        dp[cur][1] = 1;
        for(int next : list.get(cur)){
            if(next != pre){
                SNS(next, cur);
                //cur이 얼리어답터가 아니면 연결된 next는 무조건 얼리어답터여야 함
                dp[cur][0] += dp[next][1];
                //cur이 얼리어답터라면 연결된 next는 얼리어답터 여부가 상관없다. 그 중 최소값을 찾아오면 된다.
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}