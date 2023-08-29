package Implematation.p14503_로봇청소기;

import java.io.*;
import java.util.*;

public class p14503_HJ {
	static int[][] arr;
    static int n, m;
    static int ans = 1;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(dir, r, c);
        System.out.println(ans);
    }

    public static void dfs(int dir, int r, int c){
        arr[r][c] = 2;
        for (int i = 0; i < 4; i++){
            dir = (dir + 3) % 4;
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (nr >= 0 && nr < n && nc >=0 && nc < m && arr[nr][nc] == 0){
                ans++;
                dfs(dir, nr, nc);
                return;
            }
        }
        int d = (dir + 2) % 4;
        int x = r + dr[d];
        int y = c + dc[d];
        if (x >= 0 && x < n && y >=0 && y < m && arr[x][y] != 1)
            dfs(dir, x, y);
    }
}