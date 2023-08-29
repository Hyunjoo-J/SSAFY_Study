package BackTracking.p14889_스타트와링크;

import java.io.*;
import java.util.*;

public class p14889_HJ {
	static int n;
    static int[][] arr;
    static int ans = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n];

        back(0, 0);
        System.out.println(ans);
    }
    //idx는 뽑은 개수, dep은 뽑은 수
    public static void back(int idx,int dep){
        if (dep == n/2){
            int team1 = 0, team2 = 0;
            for (int i = 0; i < n - 1; i++){
                for(int j = i + 1; j < n; j++){
                    if(visited[i] == true && visited[j] == true){
                        team1 += arr[i][j];
                        team1 += arr[j][i];
                    }
                    else if(visited[i] == false && visited[j] == false){
                        team2 += arr[i][j];
                        team2 += arr[j][i];
                    }
                }
            }
            ans = Math.min(ans, Math.abs(team1-team2));
            return ;
        }
        for(int i = idx; i < n; i++){
                visited[i] = true;
                back(i + 1, dep + 1);
                visited[i] = false;
        }
    }
}
