package BackTracking.p14888_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class p14888_HJ {
	public static int n;
    public static int[] arr;
    public static int[] cal = new int[4];
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 4; i++){
            cal[i] = Integer.parseInt(st.nextToken());
        }
        dfs(arr[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int num, int idx){
        if (idx == n){
            max = Math.max(num, max);
            min = Math.min(num, min);
            return ;
        }
        for (int i = 0; i < 4; i++){
            if(cal[i] > 0){
                cal[i]--;
                switch(i) {
                    case 0:
                        dfs(num + arr[idx], idx + 1);
                        break;
                    case 1:
                        dfs(num - arr[idx], idx + 1);
                        break;
                    case 2:
                        dfs(num * arr[idx], idx + 1);
                        break;
                    case 3:
                        dfs(num / arr[idx], idx + 1);
                        break;
                }
                cal[i]++;
            }
        }

    }
}
