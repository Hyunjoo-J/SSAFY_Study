package BFS_DFS.p12851_숨바꼭질2;

import java.util.*;
import java.io.*;

public class p12851_HJ {
    static int min = Integer.MAX_VALUE;
    static int cnt = 0;
    static int n,k;
    static int[] visited = new int[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n >= k){
            System.out.println(n-k);
            System.out.println("1");
            return;
        }
        move();
        System.out.println(min + "\n" + cnt);
    }
    static void move(){
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 1;

        while (!q.isEmpty()){
            int now = q.poll();

            if(min < visited[now])
                return;
            //이미 시간 초과
            for(int i = 0; i < 3; i++){
                int next;

                if (i == 0)
                    next = now + 1;
                else if (i == 1)
                    next = now - 1;
                else
                    next = now * 2;
                if (next < 0 || next > 100000){
                    continue;
                }
                if (next == k) {
                    min = visited[now];
                    cnt++;
                }
                //한 번도 방문한적 없거나, 지금의 바로 다음에 방문할 때
                if (visited[next] == 0 || visited[next] == visited[now] + 1) {
                    q.add(next);
                    visited[next] = visited[now] + 1;
                }
            }
        }
    }
}
