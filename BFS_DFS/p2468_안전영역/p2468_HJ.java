package BFS_DFS.p2468_안전영역;

import java.io.*;
import java.util.*;

public class p2468_HJ {
    static int n;
    static int[][] arr;
    static int[] dx={1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[n][n];
        int height = 0;
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                height = Math.max(arr[i][j], height);
            }
        }

        int max = 0;
        for(int i = 0; i < height; i++){
            visited = new int[n][n];
            int cnt = 0;
            for (int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if (visited[j][k] == 0 && arr[j][k] > i){
                        area(i,j,k);
                        cnt++;
                    }
                }
            }
            max = Math.max(cnt, max);
        }
        System.out.println(max);

    }
    public static void area(int now, int x, int y){
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx > n -1 || ny < 0 || ny > n - 1)
                continue;
            if(arr[nx][ny] > now && visited[nx][ny] == 0){
                area(now, nx, ny);
            }
        }
    }
}
