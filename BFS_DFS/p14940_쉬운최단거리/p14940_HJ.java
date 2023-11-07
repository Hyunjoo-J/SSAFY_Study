package BFS_DFS.p14940_쉬운최단거리;

import java.util.*;
import java.io.*;
public class p14940_HJ {
    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static int[][] ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];
        int sx = -1, sy = -1;
        for (int i = 0; i < N; ++i){
            Arrays.fill(ans[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    sx = i;
                    sy = j;
                } else if(map[i][j] == 0) {
                    ans[i][j] = 0;
                }
            }
        }
        bfs(sx, sy);
    }

    private static void bfs(int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        ans[x][y] = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();
            int nx, ny;
            for(int i = 0; i < 4; ++i){
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if(map[nx][ny] == 0)
                    continue;
                if(ans[nx][ny] > -1)
                    continue;
                ans[nx][ny] = ans[cur.x][cur.y] + 1;
                q.add(new Node(nx, ny));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < M; ++j){
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}