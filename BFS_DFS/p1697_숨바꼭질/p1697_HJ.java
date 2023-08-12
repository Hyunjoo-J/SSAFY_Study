package BFS_DFS.p1697_숨바꼭질;

import java.io.*;
import java.util.*;

public class p1697_HJ {
    static int n,m;
    static int[] visited = new int[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(n == m) {
            System.out.println(0);
        } else {
            bfs(n);
        }
    }
    public static void bfs(int pos){
        Queue<Integer> q = new LinkedList<>();
        q.add(pos);
        visited[pos] = 1;

        while(!q.isEmpty()){
            int tmp = q.poll();

            for(int i = 0; i < 3; i++){
                int next;

                if(i == 0) {
                    next = tmp + 1;
                }else if(i == 1) {
                    next = tmp - 1;
                }else {
                    next = 2 * tmp;
                }
                if(next == m) {
                    System.out.println(visited[tmp]);
                    return;
                }
                if(next >= 0 && next <= 100000 && visited[next] == 0){
                    q.add(next);
                    visited[next] = visited[tmp] + 1;
                }
            }
        }
    }
}
