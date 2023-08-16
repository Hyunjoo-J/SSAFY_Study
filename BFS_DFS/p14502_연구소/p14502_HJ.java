import java.io.*;
import java.util.*;

public class p14502_HJ {
    public static class Pair{
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, m, max;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; ++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int dep, int x){
        if(dep == 3){
            bfs();
            return;
        }
        for(int i = x; i < n; ++i){
            for(int j = 0; j < m; ++j){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(dep + 1, i);
                    map[i][j] = 0;
                }
            }
        }
    }
    private static void bfs(){
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                if(map[i][j] == 2)
                    q.offer(new Pair(i,j));
            }
        }
        int tmp[][] = new int[n][m];
        for(int i = 0; i < n; ++i){
            tmp[i] = map[i].clone();
        }
        Pair now;
        int nx, ny;
        while(!q.isEmpty()){
            now = q.poll();
            for(int i = 0; i < 4; ++i){
                nx = now.x + dx[i];
                ny = now.y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if(tmp[nx][ny] == 0){
                    tmp[nx][ny] = 2;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        calzero(tmp);
    }

    private static void calzero(int[][] tmp){
        int cnt = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                if(tmp[i][j] == 0)
                    ++cnt;
            }
        }
        max = Math.max(max, cnt);
    }
}
