package Implematation.p17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class p17136_HJ {

	static int ans = Integer.MAX_VALUE;
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 10; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0);
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);
    }
    private static void dfs(int x, int y, int cnt){
        if(x >= 9 && y > 9){
            ans = Math.min(cnt, ans);
            return;
        }
        if(cnt >= ans){ //더 많은 색종이를 쓰는 순간 종료
            return;
        }
        if(y > 9){
            dfs(x + 1, 0, cnt);
            return ;
        }
        if(map[x][y] == 1){
            for(int i = 5; i >= 1; --i){
                if(paper[i] > 0 && isValid(x, y, i)){
                    for(int j = x; j < x + i; ++j){
                        for(int k = y; k < y + i; ++k){
                            map[j][k] = 0;
                        }
                    }
                    --paper[i];
                    dfs(x, y + 1, cnt + 1);
                    for(int j = x; j < x + i; ++j){
                        for(int k = y; k < y + i; ++k){
                            map[j][k] = 1;
                        }
                    }
                    ++paper[i];
                }
            }
        }else {
            dfs(x, y + 1, cnt);
        }
    }
    private static boolean isValid(int x, int y, int size){
        for(int i = x; i < x + size; ++i){
            for(int j = y; j < y + size; ++j){
                if(i < 0 || j < 0 || i >= 10 || j >= 10)
                    return false;
                if(map[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}