package BinarySearch.p10816_숫자카드2;

import java.io.*;
import java.util.*;

public class p10816_MJ {

    public static void main(String[] args) throws Exception{
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> numMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            int num = Integer.parseInt(st.nextToken());
            numMap.putIfAbsent(num, 0);
            int tmp = numMap.get(num);
            numMap.put(num, tmp+1);
        }

        M = Integer.parseInt(br.readLine());
        int[] ans = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; ++i){
            int num = Integer.parseInt(st.nextToken());
            numMap.putIfAbsent(num, 0);
            ans[i] = numMap.get(num);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; ++i){
            sb.append(ans[i]).append(" ");
        }
        System.out.print(sb.toString());
        br.close();
    }
}