package DynamicProgramming.p7579_앱;

import java.util.*;
import java.io.*;
public class p7579_HJ {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] mem = new int[n];
        int[] cost = new int[n];
        int[][] dp = new int[n][100001];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i)
            mem[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i)
            cost[i] = Integer.parseInt(st.nextToken());

        //knapsack 알고리즘, 배낭의 무게(비용 = j)를 1씩 늘려가며 순간 순간의 최고 효율을 찾음
        //dp 배열안에는 현재 메모리 확보한 메모리가 담김
        //dp값(메모리)는 클 수록, j(cost)는 낮을수록 좋다
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i){
            int me = mem[i];
            int c = cost[i];
            for(int j = 0; j < 100001; ++j){
                if(i == 0){
                    if(j >= c)
                        dp[i][j] = me;
                }else{
                    if(j >= c) {
                        //(이전 아이템 까지 봤을 때, 현재 아이템을 넣을 수 있는 공간 + 현재 메모리), 현재 아이템 안 넣기
                        dp[i][j] = Math.max(dp[i - 1][j - c] + me, dp[i - 1][j]);
                    }
                    else {
                        //가방이 현재 아이템을 넣지 못함, 이 전 값을 가져옴
                        dp[i][j] = dp[i - 1][j];
                    }
                }
                if(dp[i][j] >= m)
                    ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }
}