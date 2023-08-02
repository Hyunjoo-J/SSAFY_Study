package BFS.p2644_촌수계산;

import java.io.*;
import java.util.*;

public class p2644_HJ {
    static int n;
    static int m;
    static int p1,p2;
    static int[][] arr;
    static int ans = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];
        boolean[] visited = new boolean[n+1];
        int a,b;
        for(int i = 0; i < m; i++){
            st  = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }
        bfs(0, p1, visited);
        if(ans == 100)
            System.out.println("-1");
        else
            System.out.println(ans);

    }
    public static void bfs(int res, int idx, boolean[] visited){
        if(idx == p2){
            ans = Math.min(res, ans);
        }
        for(int i = 1; i <= n; i++){
            if(!visited[i] && arr[idx][i] == 1){
                visited[i] = true;
                bfs(res + 1, i, visited);
            }
        }
    }
}